INSERT INTO bookings (id, bank_account, booking_time, email, first_name, grand_total, last_name, phone_number, total_of_ticket, plan_id, account_id, process_type)
VALUES
   (1, '90004865362', '2018-12-20 08:10', '97lynk@gmail.com', 'Tuấn', 5000000, 'Nguyễn', '0837078520', 3, 1, 2, 'IN_CART'),
  (2, '9000482343687', '2018-12-17 09:30', 'mailhub.97lynk@gmail.com', 'Thiện', 340000, 'Đoàn', '08370734220', 4, 4, 2, 'IN_CART');

INSERT INTO booking_details (id, birth_date, first_name, identification, last_name, booking_id, ticket_type)
VALUES
   (1, '1997-08-11', 'Tuấn', '15110144', 'Nguyễn', 1, 'ADULT'),
   (2, '1997-11-11', 'Thiện', '15110129', 'Đoàn', 1, 'ADULT'),
   (3, '1997-03-17', 'Thông', '15110383', 'Nguyễn', 1, 'CHILD'),

  (4, '1997-03-14', 'Thiện', '15110129', 'Đoàn Huỳnh', 2, 'ADULT'),
  (5, '1997-06-23', 'Thông', '15110383', 'Nguyễn Di', 2, 'ADULT'),
  (6, '1997-02-15', 'Hiệp', '15110129', 'Nguyễn Hoàng', 2, 'ADULT'),
  (7, '1997-04-19', 'Duy Anh', '15110383', 'Nguyễn Ngọc', 2, 'CHILD');
