/*function calendar(arr) {

    const previous = ` class="prev-month"`;
    const next = ` class="next-month"`;
    const same = ` class="today"`;

    let [d, m, y] = arr;
    let today = new Date(y, m - 1, d);
    let mStart = new Date(today.getFullYear(), today.getMonth(), 1);
    let day = new Date(mStart.getFullYear(), mStart.getMonth(), mStart.getDate());

    day.setDate(mStart.getDate() - mStart.getDay());
    let html = `<table>\n  ` +
        `<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>\n`;


    function getClass(d1, d2) {
        if (d1.getMonth() === d2.getMonth()) {
            if (d1.getDate() !== d2.getDate()) {
                return ``;
            } else {
                return same;
            }
        } else if (d1.getMonth() < d2.getMonth()) {
            return (d1.getDate() < d2.getDate()) ? previous : next;
        } else {
            return (d1.getDate() < d2.getDate()) ? previous : next;
        }
    }

    do {
        html += `  <tr>`;
        for (let i = 0; i <= 6; i++) {
            html += `<td${getClass(today, day)}>${day.getDate()}</td>`;
            day.setDate(day.getDate() + 1);

        }
        html += `</tr>\n`;
    } while (day.getMonth() <= today.getMonth() && day.getFullYear() === today.getFullYear());

    console.log(html + `</table>`);
}*/

function calendar(arr) {

    let [year, month, date] = [arr[2], arr[1] - 1, arr[0]];
    let today = new Date();
    today.setFullYear(year);
    today.setMonth(month);
    today.setDate(date);
    let cDay = new Date(year, month, 1);

    let html = [];
    html.push(
        `<table>\n`,
        `  <tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>\n`);


    if (cDay.getDay() !== 0) {
        cDay.setDate(cDay.getDate() - cDay.getDay());
        html.push(`  <tr>`);
        while (cDay.getMonth() !== month){
            html.push(`<td class="prev-month">${cDay.getDate()}</td>`);
            cDay.setDate(cDay.getDate() + 1);
        }
    }

    // print current
    do {
        let d = cDay.getDay();
        if(d === 0) {
            html.push(`  <tr>`);
        }
        html.push(`<td${(cDay.getDate() !== date) ? '' : ' class="today"'}>${cDay.getDate()}</td>`);

        if (d === 6) {
            html.push(`</tr>\n`);
        }
        cDay.setDate(cDay.getDate() + 1);
    } while (cDay.getMonth() === month);

    // print next
    if (cDay.getMonth() !== month) {
        while (cDay.getDay() > 0){
            html.push(`<td class="next-month">${cDay.getDate()}</td>`);
            if (cDay.getDay() === 6) {
                html.push(`</tr>\n`);
            }
            cDay.setDate(cDay.getDate() + 1);
        }

    }

    html.push(`</table>`);

    console.log(html.join(''));
}

calendar([4, 9, 2016]);
calendar([24, 12, 2012]);
calendar([1, 1, 1900]);
calendar([1, 4, 2016]);


/*
<table>
  <tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>
  <tr><td class="prev-month">28</td><td class="prev-month">29</td><td class="prev-month">30</td><td class="prev-month">31</td><td>1</td><td>2</td><td>3</td></tr>
  <tr><td class="today">4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td></tr>
  <tr><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td></tr>
  <tr><td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td></tr>
  <tr><td>25</td><td>26</td><td>27</td><td>28</td><td>29</td><td>30</td><td class="next-month">1</td></tr>
</table>


<table>
  <tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>
  <tr><td class="prev-month">25</td><td class="prev-month">26</td><td class="prev-month">27</td><td class="prev-month">28</td><td class="prev-month">29</td><td class="prev-month">30</td><td>1</td></tr>
  <tr><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td></tr>
  <tr><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td></tr>
  <tr><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td><td>22</td></tr>
  <tr><td>23</td><td class="today">24</td><td>25</td><td>26</td><td>27</td><td>28</td><td>29</td></tr>
  <tr><td>30</td><td>31</td><td class="next-month">1</td><td class="next-month">2</td><td class="next-month">3</td><td class="next-month">4</td><td class="next-month">5</td></tr>
</table>
 */