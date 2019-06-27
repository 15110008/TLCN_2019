ALTER TABLE bookings
DROP COLUMN created_at,
DROP COLUMN created_by,
DROP COLUMN updated_at,
DROP COLUMN updated_by;

ALTER TABLE booking_details
  DROP COLUMN created_at,
  DROP COLUMN created_by,
  DROP COLUMN updated_at,
  DROP COLUMN updated_by;

ALTER TABLE places
  DROP COLUMN created_at,
  DROP COLUMN created_by,
  DROP COLUMN updated_at,
  DROP COLUMN updated_by;

ALTER TABLE plans
  DROP COLUMN created_at,
  DROP COLUMN created_by,
  DROP COLUMN updated_at,
  DROP COLUMN updated_by;

ALTER TABLE roles
  DROP COLUMN created_at,
  DROP COLUMN created_by,
  DROP COLUMN updated_at,
  DROP COLUMN updated_by;

ALTER TABLE tours
  DROP COLUMN created_at,
  DROP COLUMN created_by,
  DROP COLUMN updated_at,
  DROP COLUMN updated_by;
