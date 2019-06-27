ALTER TABLE `booking_details`
ADD COLUMN `ticket_type` varchar(255) NOT NULL;

ALTER TABLE `bookings`
ADD COLUMN `process_type` varchar(255) NOT NULL;
