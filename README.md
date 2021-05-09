# scala-lenses [![Scala CI](https://github.com/techmonad/scala-lenses/actions/workflows/scala.yml/badge.svg?branch=master)](https://github.com/techmonad/scala-lenses/actions/workflows/scala.yml)

A simple example to play with scala case classes using lenses.

```scala
  
  import Employee._
  
  val empAddress = Address("XX", "Employee Address", "1234-XX")
  val depAddress = Address("ZZ", "Department Address", "1234-ZZ")

  val department = Department(UUID.randomUUID, "DEP 1", depAddress)
  val employee = Employee(UUID.randomUUID, "EMP 1", empAddress, department)

    /**
     * Update employee -> address -> post code
     */
    // Using copy
    val emp_1 = employee.copy(address = employee.address.copy(post = "1234-YY"))
    
    // Using lens
    val emp_2 = empAddPoLens.set(employee, "1234-YY")
    
    /**
     * Update employee -> department -> address -> post code
     */
    // Using copy
    val emp_3 = employee.copy(department = employee.department.copy(address = employee.department.address.copy(post = "1234-EE")))
    
    // Using lens
    val emp_4 = empDepAddPoLens.set(employee, "1234-EE")

```
