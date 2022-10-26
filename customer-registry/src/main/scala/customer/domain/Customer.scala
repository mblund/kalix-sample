package customer.domain

import com.google.protobuf.empty.Empty
import customer.api
import kalix.scalasdk.valueentity.ValueEntity
import kalix.scalasdk.valueentity.ValueEntityContext

object Customer {
  def convertToApi(currentState: CustomerState): api.Customer =
    api.Customer(
      currentState.customerId,
      currentState.email,
      currentState.name,
      currentState.address.map(address =>
        api.Address(address.street, address.city)
      )
    )

  private def convertToDomain(customer: api.Customer): CustomerState =
    CustomerState(
      customer.customerId,
      customer.email,
      customer.name,
      customer.address.map(address =>
        Address(
          address.street,
          address.city
        )
      )
    )
}

class Customer(context: ValueEntityContext) extends AbstractCustomer {

  override def emptyState: CustomerState = CustomerState()

  override def create(
      currentState: CustomerState,
      customer: api.Customer
  ): ValueEntity.Effect[Empty] = {
    val state = Customer.convertToDomain(customer)
    effects.updateState(state).thenReply(Empty.defaultInstance)
  }

  override def getCustomer(
      currentState: CustomerState,
      getCustomerRequest: api.GetCustomerRequest
  ): ValueEntity.Effect[api.Customer] =
    if (currentState.customerId.isEmpty)
      effects.error(
        s"Customer ${getCustomerRequest.customerId} has not been created."
      )
    else
      effects.reply(Customer.convertToApi(currentState))

}
