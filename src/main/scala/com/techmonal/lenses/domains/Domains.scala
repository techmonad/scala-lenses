package com.techmonal.lenses.domains

import java.util.UUID

import com.techmonal.lenses.lens.Lens

final case class Address(
    no: String,
    street: String,
    post: String,
  )

final case class Department(
    id: UUID,
    name: String,
    address: Address,
  )

final case class Employee(
    id: UUID,
    name: String,
    address: Address,
    department: Department,
  )

object Address {

  // Address lenses
  val noLens     = Lens[Address, String](get = _.no, set = (o, v) => o.copy(no = v))
  val streetLens = Lens[Address, String](get = _.street, set = (o, v) => o.copy(street = v))
  val postLens   = Lens[Address, String](get = _.street, set = (o, v) => o.copy(post = v))
}

object Department {

  // Department lenses
  val dIdLens      = Lens[Department, UUID](get = _.id, set = (o, v) => o.copy(id = v))
  val dNameLens    = Lens[Department, String](get = _.name, set = (o, v) => o.copy(name = v))
  val dAddressLens = Lens[Department, Address](get = _.address, set = (o, v) => o.copy(address = v))

  // Composed Department lenses with Address lenses
  val depAddNoLens     = Lens.compose(dAddressLens, Address.noLens)
  val depAddStreetLens = Lens.compose(dAddressLens, Address.streetLens)
  val depAddPostLens   = Lens.compose(dAddressLens, Address.postLens)
}

object Employee {

  // Employee lenses
  val eIdLens        = Lens[Employee, UUID](get = _.id, set = (o, v) => o.copy(id = v))
  val eNameLens      = Lens[Employee, String](get = _.name, set = (o, v) => o.copy(name = v))
  val eAddressLens   = Lens[Employee, Address](get = _.address, set = (o, v) => o.copy(address = v))
  val departmentLens = Lens[Employee, Department](get = _.department, set = (o, v) => o.copy(department = v))

  // Composed lenses
  val empAddNoLens    = Lens.compose(eAddressLens, Address.noLens)
  val empAddStLens    = Lens.compose(eAddressLens, Address.streetLens)
  val empAddPoLens    = Lens.compose(eAddressLens, Address.postLens)
  val empDepIdLens    = Lens.compose(departmentLens, Department.dIdLens)
  val empDepNameLens  = Lens.compose(departmentLens, Department.dNameLens)
  val empDepAddLens   = Lens.compose(departmentLens, Department.dAddressLens)
  val empDepAddNoLens = Lens.compose(empDepAddLens, Address.noLens)
  val empDepAddStLens = Lens.compose(empDepAddLens, Address.streetLens)
  val empDepAddPoLens = Lens.compose(empDepAddLens, Address.postLens)
}
