JS Advanced: Retake Exam 5 September 2017
=========================================

Problems for exam preparation for the ["JavaScript Advanced" course @
SoftUni](https://softuni.bg/courses/javascript-advanced). Submit your
solutions in the SoftUni judge system at
<https://judge.softuni.bg/Contests/756/>.

Problem 1. Sort Table (Simple DOM Interaction)
==============================================

You are given the following **HTML code**:

<table>
    <thead>
        <tr><td>sort-table.html</td></tr>
    </thead>
    <tbody>
        <tr>
            <td>

  \<!DOCTYPE **html**\>  
                                    
  \<**html lang=\"en\"**\>                                            
  \<**head**\>                                                        
  \<**meta charset=\"UTF-8\"**\>                                      
  \<**title**\>Sort Table\</**title**\>                               
    \<**style**\>  
       **body** {  
  **padding**: 30**px**;  
  }  
  **table** {  
  **border**: 1**px solid black**;  
  **border-collapse**: **collapse**;  
  }  
  **th**, **td** {  
  **padding**: 5**px** 20**px**;  
  }  
  **th** {  
  **background**: **\#cccccc**;  
  }  
  **tr**:**nth-child**(**odd**) {  
  **background**: **\#eeeeee**;  
  }  
  **tr**:**nth-child**(**even**) {  
  **background**: **\#dddddd**;  
  }  
  **a** {  
  **color**: **black**;  
  **text-decoration**: **none**;  
  }  
  **a**:**hover** {  
  **color**: **white**;  
  }  
  .**active** {  
  **color**: **\#99ff99**;  
  }  
  \</**style**\>  
  \<**scrip src=\"https://code.jquery.com/jquery-3.1.1.min.js\"**\>\</**script**\>  
  \</**head**\>  
  \<**body**\>  
  \<**h1**\>Sort Table\</**h1**\>  
  \<**table id=\"products\"**\>  
  \<**thead**\> 
  \<**tr**\>  
  \<**th**\>Name \<**a href=\"javascript:***sort***(0,false)\"**\>**&\#x25B2;**\</**a**\>   
  \<**a href=\"javascript:***sort***(0, true)\"**\>**&\#x25BC;**\</**a**\>\</**th**\>  
  \<**th**\>Price \<**a href=\"javascript:***sort***(1, false)\"**\>**&\#x25B2;**\</**a**\>  
   \<**a href=\"javascript:***sort***(1, true)\"**\>**&\#x25BC;**\</**a**\>\</**th**\>  
  \</**tr**\>  
  \</**thead**\>  
  \<**tbody**\>  
  \<**tr**\>  
  \<**td**\>Potatoes\</**td**\>  
  \<**td**\>0.89\</**td**\>  
  \</**tr**\>  
  \<**tr**\>  
  \<**td**\>Tomatoes\</**td**\>  
  \<**td**\>2.30\</**td**\>  
  \</**tr**\>  
  \<**tr**\>  
  \<**td**\>Bananas\</**td**\>  
  \<**td**\>1.79\</**td**\>  
  \</**tr**\>  
  \</**tbody**\>  
  \</**table**\>  
  \<**script**\>  
  **function** *sort*(colIndex, descending) {  
  *//**TODO:\  
  ***}  
  \</**script**\>    
  \</**body**\>  
  \</**html**\>  

</pre>                                                     
</td></tr>
</tbody>
</table>

### Your Task

**Write the missing JavaScript function sort()**, that **sorts** the
table depending on the passed in **arguments**. The table will always
have **two** columns, first column is a string (**name** of a product)
and the second a number (**price** of a product). The first parameter is
the **column** **index** which is either 0 or 1 (name column or price
column), the second parameter is a **Boolean** variable which
**indicates** whether the sorting is **descending** or **ascending**.

-   Depending on the pressed button the sorting is either ascending or
    descending.

-   There are four buttons and each column has two.

You should **not** attach **event listeners** to the buttons, they have
already been **configured** (see the **HTML skeleton**).

### Submission

Submit only your **sort** function.

*Scroll down for examples.*

### Examples

+-----------------------------------+-----------------------------------+
| ![ascending-by-name](/media/image1| ![ascending-by-name](media/image1 |
| .png){width="2.6041666666666665in | .png){width="2.6041666666666665in |
| "                                 | "                                 |
| height="1.8854166666666667in"}    | height="1.8854166666666667in"}    |
|                                   |                                   |
| **Ascending** by **name**         | **Descending** by **name**        |
+===================================+===================================+
| ![ascending-by-price](media/image | ![descending-by-price](media/imag |
| 2.png){width="2.6555555555555554i | e3.png){width="2.4583333333333335 |
| n"                                | in"                               |
| height="1.8851476377952756in"}    | height="1.78125in"}               |
|                                   |                                   |
| **Ascending** by **price**        | **Descending** by **price**       |
+-----------------------------------+-----------------------------------+
