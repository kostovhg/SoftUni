<h3 style="color:#642d08">Bit-Snow</h3>

Winter is coming and soon it will start snowing. But until then we can still play with snow... made from **bits**.

You will get a sequence of **positive 16-bit numbers.** Once you put the **bit-representation** of all numbers **one on top of another** you will see a winter wonderland, **where 0-s represent the** cold **air and 1-s represent snowflakes.**

But snowflakes never stay still, they tend to fall gracefully and to form piles on the ground.

 For example, if you get the numbers **82, 44, 3313 and 312** you will get the scene after all the snowflakes have landed:

 ![example](https://raw.githubusercontent.com/kostovhg/SoftUni/master/Java%20Fundamentals-Sep17/Java%20Advanced/ExamPreparation/20171022Exam/src/docs/p01_BitSnow.png)

So, the numbers left are: **0, 48, 120 and 3583**. ‬‬‬‬

<h4 style="color:#8f400b;">Input</h4>

- As an input you will receive a **sequence of the positive 16-bit** integers, separated **by comma and space**.

<h4 style="color:#8f400b;">Output</h4>

- Print the numbers left after the bit-snow has piled on the ground, again separated **by a space and a comma**.

<h4 style="color:#8f400b;">Constrains</h4>

- Each of the integers in the input will be in the range **[0...2<sup>16- 1</sup>]**.
- The sequence will consist of **[0…6000]** integers.
- Allowed time/memory: **120ms/16MB**.

<h4 style="color:#8f400b;">Examples</h4>

| **Input** | **Output** |
| --- | --- |
| 82, 44, 3313, 312 | 0, 48, 120, 3583 |
| 1, 0, 0, 1, 1, 0, 0, 0 | 0, 0, 0, 0, 0, 1, 1, 1 |