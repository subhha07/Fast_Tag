FASTag Overview:

The application begins with an input box where users can enter a vehicle number. Based on the vehicle number:
  If the vehicle is already registered:
      The user is redirected to a page displaying their vehicle details and the total amount to be paid.
      On clicking Proceed, the user selects a payment method and completes the transaction.
      The entry time, vehicle type, and payment mode are recorded in the database.
  If the vehicle is not registered:
      The user is prompted to provide additional details such as the owner's name and vehicle type.
      A registration fee of 50 Rs. is applied for new registrations.

Have created VEHICLE_FARE_CONFIG table to map vehicle types to fare amounts. This data is cached to minimize repetitive database queries. Additionally, a logs table is maintained to track all vehicle activity.

Roadmap / Future Enhancements:
  Introduce a FASTag wallet system for payments.
  Implement logic to handle exempted vehicle types.
  Seperate windown like admin console to: view vehicle logs, add and modify vehicle types.
