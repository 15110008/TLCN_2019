ALTER TABLE accounts CHANGE first_name full_name varchar (255);
ALTER TABLE accounts DROP COLUMN last_name;
ALTER TABLE accounts ADD COLUMN gender BIT(1);

UPDATE accounts SET full_name = 'John Smith', gender = b'1' WHERE id = 1;
UPDATE accounts SET full_name = 'John Doe', gender = b'1' WHERE id = 2;
UPDATE accounts SET full_name = 'Haha Zaza', gender = b'1' WHERE id = 3;


