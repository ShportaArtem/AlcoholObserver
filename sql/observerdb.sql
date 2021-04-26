SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema observerdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `observerdb` ;

-- -----------------------------------------------------
-- Schema observerdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `observerdb` DEFAULT CHARACTER SET utf8 ;
USE `observerdb` ;

-- -----------------------------------------------------
-- Table `observerdb`.`punishment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`punishment` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`punishment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `borderValue` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`role` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`role` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`users` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_users_role_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `observerdb`.`role` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`company` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `users_id` INT NOT NULL,
  `punishment_id` INT NOT NULL,
  PRIMARY KEY (`id`, `users_id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_company_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_company_punishment1_idx` (`punishment_id` ASC) VISIBLE,
  CONSTRAINT `fk_company_punishment1`
    FOREIGN KEY (`punishment_id`)
    REFERENCES `observerdb`.`punishment` (`id`),
  CONSTRAINT `fk_company_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `observerdb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`verification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`verification` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`verification` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `check` TINYINT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `users_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_verification_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_verification_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `observerdb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`employee`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`employee` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`employee` (
  `users_id` INT NOT NULL,
  `countOfViolation` INT NOT NULL,
  `fine` TINYINT NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `address` VARCHAR(45) NOT NULL,
  `company_id` INT NOT NULL,
  `verification_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`users_id`),
  INDEX `fk_employee_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_employee_company1_idx` (`company_id` ASC) VISIBLE,
  INDEX `fk_employee_verification1_idx` (`verification_id` ASC) VISIBLE,
  CONSTRAINT `fk_employee_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `observerdb`.`company` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_employee_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `observerdb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_employee_verification1`
    FOREIGN KEY (`verification_id`)
    REFERENCES `observerdb`.`verification` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `observerdb`.`owner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `observerdb`.`owner` ;

CREATE TABLE IF NOT EXISTS `observerdb`.`owner` (
  `countOfCompany` INT NOT NULL,
  `users_id` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`users_id`),
  INDEX `fk_owner_users1_idx` (`users_id` ASC) VISIBLE,
  CONSTRAINT `fk_owner_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `observerdb`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
INSERT INTO role VALUES(DEFAULT, 'admin');
INSERT INTO role VALUES(DEFAULT, 'owner');
INSERT INTO role VALUES(DEFAULT, 'employee');
INSERT INTO role VALUES(DEFAULT, 'verifier');