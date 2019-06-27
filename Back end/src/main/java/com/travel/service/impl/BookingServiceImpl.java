package com.travel.service.impl;

import com.travel.api.v1.dto.BookingDetailDto;
import com.travel.api.v1.dto.BookingDto;
import com.travel.api.v1.mapper.BookingDetailMapper;
import com.travel.api.v1.mapper.BookingMapper;
import com.travel.contant.ProcessType;
import com.travel.model.Account;
import com.travel.model.Booking;
import com.travel.model.BookingDetail;
import com.travel.model.Plan;
import com.travel.repository.BookingDetailRepository;
import com.travel.repository.BookingRepository;
import com.travel.repository.PlanRepository;
import com.travel.service.BookingService;
import com.travel.service.AccountService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    private final BookingDetailRepository bookingDetailRepository;

    private final PlanRepository planRepository;

    private final AccountService userService;

    private final BookingMapper bookingMapper = BookingMapper.INSTANCE;

    private final BookingDetailMapper bookingDetailMapper = BookingDetailMapper.INSTANCE;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BookingDetailRepository bookingDetailRepository, PlanRepository planRepository, AccountService userService) {
        this.bookingRepository = bookingRepository;
        this.bookingDetailRepository = bookingDetailRepository;
        this.planRepository = planRepository;
        this.userService = userService;
    }

    /**
     * Create a new booking and it's details
     * @param bookingDto
     * @param detailList
     * @return
     */
    @Override
    public Optional<BookingDto> bookingATour(BookingDto bookingDto, List<BookingDetailDto> detailList) {
        // find and check exits plan
        Plan plan = planRepository.findById(bookingDto.getPlanId())
                .orElseThrow(() -> new ObjectNotFoundException(bookingDto.getPlanId(), Plan.class.getSimpleName()));

        // convert to entity
        Booking booking = bookingMapper.dtoToEntity(bookingDto);
        booking.setProcessType(ProcessType.IN_CART);
        // set this plan
        booking.setPlan(plan);
        // set account
        Account account = userService.selectAccountByUserName(bookingDto.getUserName());
        booking.setAccount(account);
        // store to database
        Booking savedBooking = bookingRepository.save(booking);
        // convert list detail and set booking
        List<BookingDetail> bookingDetails = detailList.stream().map(dto -> {
            BookingDetail detail = bookingDetailMapper.dtoToEntity(dto);
            detail.setBooking(savedBooking);
            return detail;
        }).collect(Collectors.toList());

        // store all details
        bookingDetailRepository.saveAll(bookingDetails);

        return Optional.of(bookingMapper.entityToDto(booking));
    }

    /**
     * Receive a specific booking by id
     * @param bookingId
     * @return
     */
    @Override
    public Optional<BookingDto> getABookingById(Long bookingId) {
        // find a booking by id
        BookingDto bookingDto = bookingMapper.entityToDto(bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ObjectNotFoundException(bookingId, Booking.class.getSimpleName())));
        return Optional.of(bookingDto);
    }

    /**
     * Delete a booking by id
     * @param bookingId
     * @return
     */
    @Override
    @Transactional
    public Optional<BookingDto> deleteABookingById(Long bookingId) {
        // for check exist
        Optional<BookingDto> bookingDto = getABookingById(bookingId);
        // delete all details
        bookingDetailRepository.deleteAllByBookingId(bookingId);
        // delete specific booking
        bookingRepository.deleteById(bookingId);

        return bookingDto;
    }

    /**
     * Get collection of a booking's details by booking id
     * @param bookingId
     * @return
     */
    @Override
    public List<BookingDetailDto> getDetailsByBookingId(Long bookingId) {
        // for check exist
        getABookingById(bookingId);
        return bookingDetailRepository.findAllByBookingId(bookingId).stream()
                .map(bookingDetailMapper::entityToDto).collect(Collectors.toList());
    }

    /**
     * Get collection booking and details with process type is IN_CART
     * @param username
     * @return
     */
    @Override
    public List<BookingDto> getBookingsInCartByUserName(String username) {
        return bookingRepository.findAllByProcessTypeAndAccount_UserName(ProcessType.IN_CART, username)
                .stream().map(booking -> {
                    BookingDto bookingDto = bookingMapper.entityToDto(booking);
                    List<BookingDetailDto> detailDtos = booking.getBookingDetails().stream().map(bookingDetailMapper::entityToDto).collect(Collectors.toList());
                    bookingDto.setDetails(detailDtos);
                    return bookingDto;
                }).collect(Collectors.toList());
    }
}
