package com.techmonal.lenses.app

import java.util.UUID

import com.techmonal.lenses.domains.{Address, Department, Employee}
import org.slf4j.LoggerFactory

object MainApp extends App {

  import Employee._

  val logger = LoggerFactory.getLogger(this.getClass)

  val empAddress = Address("XX", "Employee Address", "1234-XX")
  val depAddress = Address("ZZ", "Department Address", "1234-ZZ")

  val department = Department(UUID.randomUUID, "DEP 1", depAddress)
  val employee = Employee(UUID.randomUUID, "EMP 1", empAddress, department)

  /**
   * Print employee object
   */
  logger.info(s"Employee: ${employee}")

  /**
   * Update employee -> address -> post code
   */
  // Using copy
  val emp_1 = employee.copy(address = employee.address.copy(post = "1234-YY"))
  logger.info(s"Emp 1: ${emp_1}")

  // Using lens
  val emp_2 = empAddPoLens.set(employee, "1234-YY")
  logger.info(s"Emp 2: ${emp_2}")

  /**
   * Update employee -> department -> address -> post code
   */
  // Using copy
  val emp_3 = employee.copy(department = employee.department.copy(address = employee.department.address.copy(post = "1234-EE")))
  logger.info(s"Emp 3: ${emp_3}")

  // Using lens
  val emp_4 = empDepAddPoLens.set(employee, "1234-EE")
  logger.info(s"Emp 4: ${emp_4}")

}
