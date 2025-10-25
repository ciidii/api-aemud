CREATE TABLE member_aqida_books
(
    member_id      VARCHAR(255) NOT NULL,
    acquired       BOOLEAN,
    book_name      VARCHAR(255),
    teacher_name   VARCHAR(255),
    learning_place VARCHAR(255)
);

CREATE TABLE member_fiqh_books
(
    member_id      VARCHAR(255) NOT NULL,
    acquired       BOOLEAN,
    book_name      VARCHAR(255),
    teacher_name   VARCHAR(255),
    learning_place VARCHAR(255)
);

ALTER TABLE member
    ADD arabic_proficiency VARCHAR(255);

ALTER TABLE member
    ADD coran_level VARCHAR(255);

ALTER TABLE member_aqida_books
    ADD CONSTRAINT fk_member_aqida_books_on_member FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE member_fiqh_books
    ADD CONSTRAINT fk_member_fiqh_books_on_member FOREIGN KEY (member_id) REFERENCES member (id);

DROP TABLE sender_template_sms CASCADE;

ALTER TABLE member
DROP
COLUMN aemud_courses;

ALTER TABLE member
DROP
COLUMN other_courses;

ALTER TABLE recipient_template
DROP
COLUMN senders;

ALTER TABLE contribution
DROP
COLUMN month;

ALTER TABLE contribution
    ADD month date;