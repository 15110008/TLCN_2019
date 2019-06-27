ALTER TABLE bookings CHANGE first_name full_name varchar (255);
ALTER TABLE bookings DROP COLUMN last_name;
ALTER TABLE bookings ADD COLUMN gender BIT(1);

ALTER TABLE booking_details CHANGE first_name full_name varchar (255);
ALTER TABLE booking_details DROP COLUMN last_name;
ALTER TABLE booking_details ADD COLUMN gender BIT(1);

UPDATE bookings SET full_name = 'Nguyễn Anh Tuấn', gender = b'1' WHERE id = 1;
UPDATE bookings SET full_name = 'Đoàn Huỳnh Thiện', gender = b'1' WHERE id = 2;

UPDATE booking_details SET full_name = 'Nguyễn Anh Tuấn', gender = b'1' WHERE id = 1;
UPDATE booking_details SET full_name = 'Đoàn Huỳnh Thiện', gender = b'1' WHERE id = 2;
UPDATE booking_details SET full_name = 'Nguyễn Di Thông', gender = b'1' WHERE id = 3;

UPDATE booking_details SET full_name = 'Đoàn Huỳnh Thiện', gender = b'1' WHERE id = 4;
UPDATE booking_details SET full_name = 'Nguyễn Di Thông', gender = b'1' WHERE id = 5;
UPDATE booking_details SET full_name = 'Nguyễn Hoàng Hiệp', gender = b'1' WHERE id = 6;
UPDATE booking_details SET full_name = 'Nguyễn Ngọc Duy Anh', gender = b'1' WHERE id = 7;