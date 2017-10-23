## Problems description

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


___


<h3 style="color:#642d08">Little Alchemy</h3>

Professor X. has finally come up with an idea how to become rich. He designed a special **acid** , which applied in a precise amount, transforms **stone** into pure **gold**. Calculating how much acid is needed for each stone is hard, that&#39;s why Professor X has **labeled each stone with a number** , showing how many **doses** it needs to turn into the valuable metal.

The stones are laid one after another on a table and every time he takes **the first (leftmost) stone**, he applies acid on it and returns it **on the (right) end** of the sequence. For example, he starts with stones **2 3 4**. On the first turn he will take **2** , apply acid and change the label on it to **1** (because there is only one dose left to be applied). Then, he places it on the **end of the sequence** and continue doing so.

 ![example](https://raw.githubusercontent.com/kostovhg/SoftUni/master/Java%20Fundamentals-Sep17/Java%20Advanced/ExamPreparation/20171022Exam/src/docs/p02_Alchemy.png)

When the required amount of acid is applied on a stone **(label becomes 0)**, it is moved from the table to the **storage** – a secret place, where Professor X keeps the gold. Because oxygen can revert the effect of the acid, he keeps the storage **air free**.

Unfortunately, there are some **air**** leaks **into the storage, which make the gold** pieces turn back into stones **. Every time there is a leak, the** last ****piece that got into the storage gets damaged** and the Professor has to calculate again the acid doses he needs for it to turn in back into gold. After labeling the stone again, he returns it back to the table - **at the end** of the sequence.

<h4 style="color:#8f400b;">Input</h4>

- On the first line of the input you will receive the **sequence of the stones** the Professor has at the beginning, each represented by an **integer**** – its label **. The stones will be separated** by space**.
- Afterwards you will get a series of commands of the following types:

- **&quot;Apply acid {n}&quot;** – Prof. X applies **n** doses of acid on **n** number of stones and moves each at the **end**.  If there are **no** stones at the table, **ignore** the command.
- **&quot;Air Leak {m}&quot;** – there is a leak in the storage, the **last piece gets damaged** and must be treated with **n** more doses to turn into gold again. If the storage is **empty** , **ignore** the command.
- &quot; **Revision**&quot; – end of input.

<h4 style="color:#8f400b;">Output</h4>

After receiving the &quot; **Revision**&quot; command you should print

- On the first line - all **stones** left, **in their current order on the table**
- On the second line - the amount of **gold pieces in the storage**

<h4 style="color:#8f400b;">Constrains<h4>

- Each of the integers in the input will be in the range **[1…3000]**.
- The sequence will consist of **[1…10000]** integers.
- **All data** must be processed **by order of input**.
- Allowed time/memory: **200ms/16MB**.

<<h4 style="color:#8f400b;">Examples</h4>

| **Input** | **Output** | **Comments** |
| ------ | ------ | ------ |
| 1 2 1 4 5   <br />    Apply acid 2 - Apply acid 1 - Air leak 5 - Apply acid 3 - Revision  | 5 3 42 | **First** and **second** stones get **1** dose of acid, so the first **turns into gold** and the second is put at the end of the sequence with **decremented** label:   **1 4 5 1.**** First **stone turns into gold:  ** 4 5 1. **Air leaks into the storage and the l** ast piece of gold **transforms into** stone **with label &quot;** 5 **&quot;:  ** 4 5 1 5. ****First** , **second** and **third** stones get 1 dose of acid and only the **second**** turns into gold **:** 5 3 4. **At the end there are** 2 **pieces of** gold **in the** storage**. |
| 5 5 1 2 5  <br />  Apply acid 2  <br />  Air leak 5  <br />  Apply acid 1  <br />  Revision | 2 5 4 4  <br />  1 | **1 2 5 4 4**</br>1 2 5 4 4  (There is **nothing** in the storage, so the command is **ignored**)</br>**2 5 4 4** |