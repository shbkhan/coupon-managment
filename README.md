# Coupon Management API

## Objective
Build a RESTful API to manage and apply different types of discount coupons:

- Cart-wise Coupons  
- Product-wise Coupons  
- Buy X Get Y (BxGy) Coupons

# 📌 Architecture and Design
## Design Principles Used
* Strategy Pattern (To support multiple coupon types)
* Open/Closed Principle (New coupon types can be added without modifying existing logic)
* Separation of Concern (Controller -> Service -> Business Logic)
* Clean DTO based API layer.

# 📌 Tech stack
* Java
* Spring boot
* Spring Data JPA
* MySql
* Maven
* Swagger

# 📌 API Endpoints

## 1️⃣ Coupon Management APIs (CRUD)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | /coupons | Create a new coupon |
| GET    | /coupons | Retrieve all coupons |
| GET    | /coupons/{id} | Retrieve a specific coupon by ID |
| PUT    | /coupons/{id} | Update an existing coupon |
| DELETE | /coupons/{id} | Delete a coupon |

---

## 2️⃣ Coupon Application APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /applicable-coupons | Fetch all applicable coupons for a given cart and calculate discount for each |
| POST | /apply-coupon/{id} | Apply a specific coupon to the cart and return updated cart with discounts |

---

## 📥 Sample Request – Create Coupon
<b>For cart-wise coupon:</b>
json
{
  "type": "string",
  "id": "string",
  "description": "string",
  "isActive": "string",
  "expirationDate": "2026-03-01T21:38:35.567Z",
  "thresholdAmount": 0.0,
  "discountPercentage": 0.0
}

<b>For product-wise coupon:</b>
json 
{
  "type": "string",
  "id": "string",
  "description": "string",
  "isActive": "string",
  "expirationDate": "2026-03-01T21:38:35.567Z",
  "productId": 0,
  "discountPercentage": 0.0
}

<b>For bxgy coupon:</b>
json
{
  "type": "string",
  "id": "string",
  "description": "string",
  "isActive": "string",
  "expirationDate": "2026-03-01T21:36:18.376Z",
  "buyProduct": [
    {
      "productId": 0,
      "role": "string"
    }
  ],
  "getProduct": [
    {
      "productId": 0,
      "role": "string"
    }
  ],
  "repitationLimit": 0,
  "buyQuantity": 0,
  "getQuantity": 0
}


---

## 📥 Sample Request – Get Applicable Coupons

json
[
  {
    "productId": 0,
    "quantity": 0,
    "totalDiscount": 0.1
  },
  {
    "productId": 0,
    "quantity": 0,
    "totalDiscount": 0.1
  }
]


---

## 📥 Sample Request – Apply Coupon

json
[
  {
    "productId": 0,
    "quantity": 0,
  },
  {
    "productId": 0,
    "quantity": 0,
  }
]

## Table structure and DB design

| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_MST_COUPONS** | ID | BIGINT | PRIMARY KEY, AUTO INCREMENT | Unique identifier for coupon |
| | TYPE | VARCHAR(20) | NOT NULL | CART_WISE, PRODUCT_WISE, BXGY |
| | DESCRIPTION | VARCHAR(300) | | Summary of the offer |
| | IS_ACTIVE | CHAR(1) | DEFAULT 1 | Soft disabling of the coupon |
| | EXPIRATION_DATE | TIMESTAMP | | Date after which coupon is invalid |
| | CREATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | For auditing and tracking |
| | UPDATED_AT | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | |



| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_CART_WISE_DETAIL** | ID | VARCHAR(10) | PRIMARY KEY | |
| | COUPON_ID | BIGINT | FOREIGN KEY FOR T_MST_COUPON | Link to master coupon |
| | THRESHOLD_AMOUNT | DECIMAL(10,2) | NOT NULL | The minimum cart value required to be applicable |
| | DISCOUNT_PERCENTAGE | DECIMAL(5,2) | NOT NULL | Percentage discount |


| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_PRODUCT_WISE_DETAILS** | ID | VARCHAR(10) | PRIMARY KEY | |
| | COUPON_ID | BIGINT | FOREIGN KEY FOR T_MST_COUPON | Link to master coupon |
| | PRODUCT_ID | BIGINT | NOT NULL | The id of the product to which it can be applicable |
| | DISCOUNT_PERCENTAGE | DECIMAL(5,2) | NOT NULL | Percentage discount |


| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_BXGY_DETAILS** | ID | VARCHAR(10) | PRIMARY KEY | |
| | COUPON_ID | BIGINT | FOREIGN KEY FOR T_MST_COUPON | Link to master coupon |
| | REPETITION_LIMIT | INT | | Max time the deal can be applied to one cart |
| | BUY_QUANTITY | INT | | Quantity to Buy to be eligible for the coupon |
| | GET_QUANTITY | INT | | Quantity to get after the eligibility |


| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_BXGY_PRODUCTS_ARRAY** | ID | BIGINT | PRIMARY KEY, AUTO INCREMENT | |
| | BXGY_DETAIL_ID | VARCHAR(10) | FOREIGN KEY FOR T_BXGY_DETAIL | |
| | PRODUCT_ID | BIGINT | NOT NULL | Product ID that is applicable |
| | ROLE | VARCHAR(10) | NOT NULL | BUY OR GET |


| Table Name | Column Name | Data Type | Constraint | Description |
| :--- | :--- | :--- | :--- | :--- |
| **T_PRODUCT** | ID | LONG | PRIMARY KEY | |
| | PRICE | DECIMAL(10,2) | | |

> **Note:** The table **T_PRODUCT** was generated at the very last; hence, it has no mapping to other tables. For future enhancement, we can perform linking between the tables that will enable us to directly fetch the price as per the product ID. Additionally, we can provide an endpoint to add products.


# 📌 Cases in consideration for implementation

## 0. Irrespective of the Coupon type
Scenarios handled and considered irrespective of the coupon type
| S.No | Case | Description | Status |
| :--- | :--- | :--- | :--- |
|1. | Duplicated Coupon | Coupon Already present in the system | Implemented |
|2. | Invalid coupon | Trying to apply coupon that is not in the system | Implemented |
|3. | Coupon Not found | Trying to Get a coupon that is not present in the system | Implemented |
|4. | Bad Request | If the end point is tried to be hit with incorrect RequestBody or PathVariable | Implemented |
|5. | Updating the coupon i.e. not present | When a user is trying to modify a coupon that is not present is the system | Implemented |
|6. | Deleting a coupon i.e. not present | Trying to delete a coupon that is not present in the system | Implemented |
|7. | Not applicable coupons | User tried to get all the applicable coupons, but the cart has no such element to which a coupon is present in the system | Implemented |
|8. | Coupon expired | Trying to applying a coupon that has been expired | Implemented |
|9. | Coupon Inactive | Coupon has been marked inactive for the use | Implemented |
|10. | Transaction | Some error occured while writing in the db, everything should be rolled back. | Implemented |

## 1. Cart-wise Coupons
Applies discount to entire cart if the threshold condition is met.

| S.No | Case | Description | Status |
| :--- | :--- | :--- | :--- |
| 1. | Percentage Discount | 10% off on cart > 1000 | Implemented |
| 2. | Less cart value | Value of the cart is less than the threshold amount | Implemented |
| 3. | Flat discount | Flat discount on the cart, like 100 off | Not implemented |
| 4. | Single use per user | One user can use the coupon only once | Not implemented |
| 5. | Max discount cap | What is the maximum discount a cart can avail | Not implemented |

> **Limitations:** Since we have not implemented the user sign in for now, hence we are unable to track the coupons used by every single user. Once it is implemented, upon a little modification in db and code we can track and restrict that one coupon is used only once by every user. Also, we can store a value while adding a coupon, that how many times a user can use a single coupon.

## 2. Product-wise Coupons
Applies discount to only specific product.

| S.No | Case | Description | Status |
| :--- | :--- | :--- | :--- |
| 1. | Single Product Discount | 10% off on Product A | Implemented |
| 2. | Percentage Discount | x% of on Product y | Implemented |
| 3. | Product not present in cart | While applying if the product applicable for coupon is not present in the car | Implemented |
| 4. | Single use per user | One user can use the coupon only once | Not implemented |
| 5. | Max discount cap | What is the maximum discount a cart can avail | Not implemented |
| 6. | Multiple product discount | 10% off on Product A and B | Not implemented |

## 3. BxGy Coupons
Buy specified quantity from Buy Array and get items from Get array free.
| S.No | Case | Description | Status |
| :--- | :--- | :--- | :--- |
| 1. | Buy x Get y | Buy x number of products from [a, b, c, d] get y number of products from [u, v, w] | Implemented  |
| 2. | Profit calculation | Based on the product getting for free, calculating the amount in profit | Implemented |
| 3. | Repetition Limit | Let's say that there are 4 product from the BUY list and 2 products from the GET list and the offer is buy 2 get 1, but the repetition limit is 1, in this scenario only 1 product must be marked free and the price for the 2nd free product should be in added in the cart amount. |  |
|4. | Buy met but no Get Amount in the cart | Let's say the offer is Buy 2 Get 1 free, The cart has 2 element from the BUY list but the item from the Get list is not present |   |
| 5. | Cheapest item free | From the GET list the cheapest item is marked as free in the cart | |
| 6. | Get item automatically added in the cart | From the pointer 4 the cheapest item from GET list can be automatically added in the cart |  |
| 7. | Same item from the BUY List | Let's say the offer is BUY 2 GET 1 FREE, and the cart has same 2 product from the BUY List |  |


## 📌 Assumptions
* Only one coupon is implemented at a time.
* All the products are in same currency (let's say INR)
* Anyone without login can access the applicable coupons and apply the coupons


## 📌 Limitations
* Authentication and role based access to the CRUD operation for the coupons should be allowed. Person not authorised should not be able to add, modify, delete and get all the coupons. Due to time constraint it is not implemented
* Performance not optimized for a very large cart.
