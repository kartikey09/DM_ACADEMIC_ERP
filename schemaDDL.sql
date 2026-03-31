-- =============================================================================
-- UNIVERSITY ACADEMIC MANAGEMENT SYSTEM — MySQL DDL
-- =============================================================================
-- Generation date : 2026-03-30
-- Engine          : InnoDB (all tables)
-- Charset         : utf8mb4 / utf8mb4_unicode_ci
-- Notes:
--   • All PKs use BIGINT UNSIGNED AUTO_INCREMENT
--   • All FKs have explicit ON DELETE / ON UPDATE actions
--   • Indexes are added on every FK column and common query columns
--   • ENUMs mirror the UML ProgramType, AssessmentType, AttendanceStatus
--   • DECIMAL precision follows academic-grade conventions
-- =============================================================================

-- ----------------------------------------------------------------------------
-- Bootstrap
-- ----------------------------------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;
SET SQL_MODE = 'STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS university_db;
CREATE DATABASE university_db
    CHARACTER SET utf8mb4
    COLLATE      utf8mb4_unicode_ci;

USE university_db;

-- ============================================================================
-- 1. DEPARTMENT
-- ============================================================================
CREATE TABLE department (
    department_id   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    name            VARCHAR(150)     NOT NULL,

    CONSTRAINT pk_department PRIMARY KEY (department_id),
    CONSTRAINT uq_department_name UNIQUE (name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Academic departments of the university';


-- ============================================================================
-- 2. PROGRAM
-- ============================================================================
CREATE TABLE program (
    program_id      BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    department_id   BIGINT UNSIGNED  NOT NULL,
    name            VARCHAR(150)     NOT NULL,
    type            ENUM(
                        'B_TECH',
                        'M_TECH',
                        'IM_TECH',
                        'PHD'
                    )                NOT NULL,
    duration_years  TINYINT UNSIGNED NOT NULL
                        CHECK (duration_years BETWEEN 1 AND 7),

    CONSTRAINT pk_program           PRIMARY KEY (program_id),
    CONSTRAINT fk_program_dept      FOREIGN KEY (department_id)
                                        REFERENCES department (department_id)
                                        ON DELETE RESTRICT
                                        ON UPDATE CASCADE,
    CONSTRAINT uq_program_dept_name UNIQUE (department_id, name)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Academic programs offered per department';

CREATE INDEX idx_program_dept ON program (department_id);
CREATE INDEX idx_program_type ON program (type);


-- ============================================================================
-- 3. STUDENT
-- ============================================================================
CREATE TABLE student (
    student_id      BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    department_id   BIGINT UNSIGNED  NOT NULL,
    program_id      BIGINT UNSIGNED  NOT NULL,
    enrollment_no   VARCHAR(30)      NOT NULL,
    first_name      VARCHAR(100)     NOT NULL,
    last_name       VARCHAR(100)     NOT NULL,
    date_of_birth   DATE             NOT NULL,
    email           VARCHAR(255)     NOT NULL,
    admission_year  YEAR             NOT NULL,
    status          ENUM(
                        'ACTIVE',
                        'GRADUATED',
                        'DROPPED',
                        'ON_LEAVE',
                        'SUSPENDED'
                    )                NOT NULL DEFAULT 'ACTIVE',

    CONSTRAINT pk_student           PRIMARY KEY (student_id),
    CONSTRAINT uq_student_enroll_no UNIQUE (enrollment_no),
    CONSTRAINT uq_student_email     UNIQUE (email),
    CONSTRAINT fk_student_dept      FOREIGN KEY (department_id)
                                        REFERENCES department (department_id)
                                        ON DELETE RESTRICT
                                        ON UPDATE CASCADE,
    CONSTRAINT fk_student_program   FOREIGN KEY (program_id)
                                        REFERENCES program (program_id)
                                        ON DELETE RESTRICT
                                        ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Registered students';

CREATE INDEX idx_student_dept       ON student (department_id);
CREATE INDEX idx_student_program    ON student (program_id);
CREATE INDEX idx_student_status     ON student (status);
CREATE INDEX idx_student_adm_year   ON student (admission_year);


-- ============================================================================
-- 4. PROFESSOR
-- ============================================================================
CREATE TABLE professor (
    professor_id    BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    name            VARCHAR(200)     NOT NULL,
    email           VARCHAR(255)     NOT NULL,
    designation     VARCHAR(100)     NOT NULL
                        COMMENT 'e.g. Assistant Professor, Associate Professor, Professor',

    CONSTRAINT pk_professor         PRIMARY KEY (professor_id),
    CONSTRAINT uq_professor_email   UNIQUE (email)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Teaching faculty';


-- ============================================================================
-- 5. COURSE
-- ============================================================================
CREATE TABLE course (
    course_id       BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    course_code     VARCHAR(20)      NOT NULL,
    title           VARCHAR(200)     NOT NULL,
    credits         TINYINT UNSIGNED NOT NULL
                        CHECK (credits BETWEEN 1 AND 6),

    CONSTRAINT pk_course            PRIMARY KEY (course_id),
    CONSTRAINT uq_course_code       UNIQUE (course_code)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Master catalogue of courses';


-- ============================================================================
-- 6. SEMESTER
-- ============================================================================
CREATE TABLE semester (
    semester_id     BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    term            ENUM(
                        'ODD',
                        'EVEN',
                        'SUMMER'
                    )                NOT NULL,
    year            YEAR             NOT NULL,

    CONSTRAINT pk_semester          PRIMARY KEY (semester_id),
    CONSTRAINT uq_semester_term_yr  UNIQUE (term, year)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Academic semesters / terms';


-- ============================================================================
-- 7. COURSE OFFERING
--    One specific run of a course in a given semester
-- ============================================================================
CREATE TABLE course_offering (
    offering_id     BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    course_id       BIGINT UNSIGNED  NOT NULL,
    semester_id     BIGINT UNSIGNED  NOT NULL,
    schedule_days   VARCHAR(20)      NOT NULL
                        COMMENT 'e.g. MON,WED,FRI',
    start_time      TIME             NOT NULL,
    max_capacity    SMALLINT UNSIGNED NOT NULL DEFAULT 60
                        CHECK (max_capacity > 0),
    enrolled_count  SMALLINT UNSIGNED NOT NULL DEFAULT 0,

    CONSTRAINT pk_course_offering       PRIMARY KEY (offering_id),
    CONSTRAINT uq_offering_course_sem   UNIQUE (course_id, semester_id),
    CONSTRAINT fk_offering_course       FOREIGN KEY (course_id)
                                            REFERENCES course (course_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_offering_semester     FOREIGN KEY (semester_id)
                                            REFERENCES semester (semester_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT chk_offering_capacity    CHECK (enrolled_count <= max_capacity)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'A course run in a specific semester';

CREATE INDEX idx_offering_course    ON course_offering (course_id);
CREATE INDEX idx_offering_semester  ON course_offering (semester_id);


-- ============================================================================
-- 8. COURSE INSTRUCTOR
--    Which professors teach which offering
-- ============================================================================
CREATE TABLE course_instructor (
    offering_id     BIGINT UNSIGNED  NOT NULL,
    professor_id    BIGINT UNSIGNED  NOT NULL,
    role            ENUM(
                        'PRIMARY',
                        'CO_INSTRUCTOR',
                        'GUEST'
                    )                NOT NULL DEFAULT 'PRIMARY',

    CONSTRAINT pk_course_instructor     PRIMARY KEY (offering_id, professor_id),
    CONSTRAINT fk_ci_offering           FOREIGN KEY (offering_id)
                                            REFERENCES course_offering (offering_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_ci_professor          FOREIGN KEY (professor_id)
                                            REFERENCES professor (professor_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Faculty assigned to a course offering';

CREATE INDEX idx_ci_professor       ON course_instructor (professor_id);


-- ============================================================================
-- 9. COURSE TA
--    Students acting as teaching assistants for an offering
-- ============================================================================
CREATE TABLE course_ta (
    student_id      BIGINT UNSIGNED  NOT NULL,
    offering_id     BIGINT UNSIGNED  NOT NULL,
    role            ENUM(
                        'HEAD_TA',
                        'TA'
                    )                NOT NULL DEFAULT 'TA',

    CONSTRAINT pk_course_ta             PRIMARY KEY (student_id, offering_id),
    CONSTRAINT fk_ta_student            FOREIGN KEY (student_id)
                                            REFERENCES student (student_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_ta_offering           FOREIGN KEY (offering_id)
                                            REFERENCES course_offering (offering_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Students serving as TAs for a course offering';

CREATE INDEX idx_ta_offering        ON course_ta (offering_id);


-- ============================================================================
-- 10. ENROLLMENT
--     A student registered in a specific course offering
-- ============================================================================
CREATE TABLE enrollment (
    enrollment_id   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    student_id      BIGINT UNSIGNED  NOT NULL,
    offering_id     BIGINT UNSIGNED  NOT NULL,
    enrollment_date DATE             NOT NULL,
    status          ENUM(
                        'REGISTERED',
                        'DROPPED',
                        'COMPLETED',
                        'INCOMPLETE',
                        'AUDIT'
                    )                NOT NULL DEFAULT 'REGISTERED',

    CONSTRAINT pk_enrollment            PRIMARY KEY (enrollment_id),
    CONSTRAINT uq_enrollment_stu_off    UNIQUE (student_id, offering_id),
    CONSTRAINT fk_enroll_student        FOREIGN KEY (student_id)
                                            REFERENCES student (student_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_enroll_offering       FOREIGN KEY (offering_id)
                                            REFERENCES course_offering (offering_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Student enrollment in course offerings';

CREATE INDEX idx_enroll_student     ON enrollment (student_id);
CREATE INDEX idx_enroll_offering    ON enrollment (offering_id);
CREATE INDEX idx_enroll_status      ON enrollment (status);


-- ============================================================================
-- 11. GRADE
--     Final grade for a student's enrollment
-- ============================================================================
CREATE TABLE grade (
    grade_id        BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    enrollment_id   BIGINT UNSIGNED  NOT NULL,
    marks_obtained  DECIMAL(5, 2)    NOT NULL CHECK (marks_obtained >= 0),
    max_marks       DECIMAL(5, 2)    NOT NULL CHECK (max_marks > 0),
    grade_letter    CHAR(3)          NOT NULL
                        COMMENT 'e.g. A+, A, B+, B, C, F',

    CONSTRAINT pk_grade                 PRIMARY KEY (grade_id),
    CONSTRAINT uq_grade_enrollment      UNIQUE (enrollment_id),
    CONSTRAINT fk_grade_enrollment      FOREIGN KEY (enrollment_id)
                                            REFERENCES enrollment (enrollment_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE,
    CONSTRAINT chk_grade_marks          CHECK (marks_obtained <= max_marks)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Final grade per enrollment';


-- ============================================================================
-- 12. ASSESSMENT
--     Individual assessment events within a course offering
-- ============================================================================
CREATE TABLE assessment (
    assessment_id   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    offering_id     BIGINT UNSIGNED  NOT NULL,
    type            ENUM(
                        'QUIZ',
                        'MIDSEM',
                        'ENDSEM',
                        'ASSIGNMENT',
                        'PROJECT'
                    )                NOT NULL,
    max_marks       DECIMAL(6, 2)    NOT NULL CHECK (max_marks > 0),
    weightage       DECIMAL(5, 2)    NOT NULL
                        COMMENT 'Percentage weight toward final grade (0-100)',
    assessment_date DATE             NOT NULL,

    CONSTRAINT pk_assessment            PRIMARY KEY (assessment_id),
    CONSTRAINT fk_assess_offering       FOREIGN KEY (offering_id)
                                            REFERENCES course_offering (offering_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE,
    CONSTRAINT chk_assess_weightage     CHECK (weightage BETWEEN 0.00 AND 100.00)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Quizzes, exams, and assignments for a course offering';

CREATE INDEX idx_assess_offering    ON assessment (offering_id);
CREATE INDEX idx_assess_type        ON assessment (type);


-- ============================================================================
-- 13. STUDENT ASSESSMENT
--     A student's score on a specific assessment
-- ============================================================================
CREATE TABLE student_assessment (
    student_assessment_id   BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    student_id              BIGINT UNSIGNED NOT NULL,
    assessment_id           BIGINT UNSIGNED NOT NULL,
    marks_obtained          DECIMAL(6, 2)   NOT NULL
                                CHECK (marks_obtained >= 0),

    CONSTRAINT pk_student_assessment        PRIMARY KEY (student_assessment_id),
    CONSTRAINT uq_sa_student_assessment     UNIQUE (student_id, assessment_id),
    CONSTRAINT fk_sa_student                FOREIGN KEY (student_id)
                                                REFERENCES student (student_id)
                                                ON DELETE RESTRICT
                                                ON UPDATE CASCADE,
    CONSTRAINT fk_sa_assessment             FOREIGN KEY (assessment_id)
                                                REFERENCES assessment (assessment_id)
                                                ON DELETE CASCADE
                                                ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Individual student marks per assessment';

CREATE INDEX idx_sa_student         ON student_assessment (student_id);
CREATE INDEX idx_sa_assessment      ON student_assessment (assessment_id);


-- ============================================================================
-- 14. ATTENDANCE
-- ============================================================================
CREATE TABLE attendance (
    attendance_id   BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    student_id      BIGINT UNSIGNED  NOT NULL,
    offering_id     BIGINT UNSIGNED  NOT NULL,
    date            DATE             NOT NULL,
    status          ENUM(
                        'PRESENT',
                        'ABSENT'
                    )                NOT NULL,
    created_at      TIMESTAMP        NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT pk_attendance            PRIMARY KEY (attendance_id),
    CONSTRAINT uq_attendance_record     UNIQUE (student_id, offering_id, date),
    CONSTRAINT fk_att_student           FOREIGN KEY (student_id)
                                            REFERENCES student (student_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_att_offering          FOREIGN KEY (offering_id)
                                            REFERENCES course_offering (offering_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Daily class attendance per student per offering';

CREATE INDEX idx_att_student        ON attendance (student_id);
CREATE INDEX idx_att_offering       ON attendance (offering_id);
CREATE INDEX idx_att_date           ON attendance (date);
CREATE INDEX idx_att_status         ON attendance (status);


-- ============================================================================
-- 15. SEMESTER RESULT
--     Per-student aggregated result for one semester
-- ============================================================================
CREATE TABLE semester_result (
    result_id           BIGINT UNSIGNED  NOT NULL AUTO_INCREMENT,
    student_id          BIGINT UNSIGNED  NOT NULL,
    semester_id         BIGINT UNSIGNED  NOT NULL,
    semester_gpa        DECIMAL(4, 2)    NOT NULL
                            CHECK (semester_gpa BETWEEN 0.00 AND 10.00),
    total_credits       SMALLINT UNSIGNED NOT NULL,
    credits_earned      SMALLINT UNSIGNED NOT NULL,
    backlogs            SMALLINT UNSIGNED NOT NULL DEFAULT 0,
    total_grade_points  DECIMAL(7, 2)    NOT NULL CHECK (total_grade_points >= 0),
    result_status       ENUM(
                            'PASS',
                            'FAIL',
                            'WITHHELD',
                            'INCOMPLETE'
                        )                NOT NULL,

    CONSTRAINT pk_semester_result       PRIMARY KEY (result_id),
    CONSTRAINT uq_sem_result_stu_sem    UNIQUE (student_id, semester_id),
    CONSTRAINT fk_semres_student        FOREIGN KEY (student_id)
                                            REFERENCES student (student_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT fk_semres_semester       FOREIGN KEY (semester_id)
                                            REFERENCES semester (semester_id)
                                            ON DELETE RESTRICT
                                            ON UPDATE CASCADE,
    CONSTRAINT chk_semres_credits       CHECK (credits_earned <= total_credits)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Computed GPA and credit summary per student per semester';

CREATE INDEX idx_semres_student     ON semester_result (student_id);
CREATE INDEX idx_semres_semester    ON semester_result (semester_id);


-- ============================================================================
-- 16. CUMULATIVE RESULT
--     Overall CGPA record — one row per student
-- ============================================================================
CREATE TABLE cumulative_result (
    student_id      BIGINT UNSIGNED  NOT NULL,
    cgpa            DECIMAL(4, 2)    NOT NULL
                        CHECK (cgpa BETWEEN 0.00 AND 10.00),
    total_credits   SMALLINT UNSIGNED NOT NULL DEFAULT 0,

    CONSTRAINT pk_cumulative_result     PRIMARY KEY (student_id),
    CONSTRAINT fk_cumres_student        FOREIGN KEY (student_id)
                                            REFERENCES student (student_id)
                                            ON DELETE CASCADE
                                            ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = 'Running cumulative GPA per student';


-- ============================================================================
-- Re-enable FK checks
-- ============================================================================
SET FOREIGN_KEY_CHECKS = 1;

-- ============================================================================
-- END OF DDL
-- ============================================================================
