# Online shop
### Study project for the course Java Web Development (Epam systems)
### Author: Traulko Yan

### Description
The customer browses the assortment of the store and fills the basket, after which he can place an order if he has enough funds in his account. The order is sent to the administrator for consideration, until the order is approved or rejected, the user can cancel it. 
### Users:
##### 1. Guest (unauthorized user)

    Functionality:

        1. Catalog browsing
        2. Login/Register
        3. Changing language
        4. "Forgot password"
##### 2. User

The user has 3 statuses: *ENABLE*, *BLOCKED*, *NOT CONFIRMED*. After registration, the user receives the status *NOT* *CONFIRMED*, since he needs to confirm the registration by mail. *ENABLE* user can be blocked by the administrator, that is, get the *BLOCKED* status. You can successfully log in only with the *ENABLE* status. 

    Functionality:
    
        1. Catalog browsing
        2. Changing language
        3. Restore password
        4. View basket
        5. Add product to basket
        6. Delete product from basket
        7. Send an order for review
        8. Undo an order not reviewed by the administrator
        9. Fill up balance
        10. Logout
        11. View order history
        
##### 3. Administrator

The administrator fills the site with goods, changes their description if necessary, reviews orders, manages users.

    Functionality:
    
        1. Catalog browsing
        2. Changing language
        3. Restore password
        4. Block users
        5. Delete not comfirmed users
        6. View all order history
        7. View user history
        8. Add product
        9. Edit product
        10. Reject/produce orders
        11. Logout
    
### **Objects:** **product**, **order**, **basket**.
The *product* has parameters such as name, description, price. Archiving is under development. The shopping *basket* is a repository of goods that the user is going to purchase in the future. If there is the required amount of money, the user can send all the goods contained in the *basket* to the administrator in the form of an *order* for consideration. The *order* can be either approved, then the money will be debited from the buyer's account, or canceled.