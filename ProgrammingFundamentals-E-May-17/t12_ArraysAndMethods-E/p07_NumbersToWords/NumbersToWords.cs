using System;

namespace p07_NumbersToWords
{
    class NumbersToWords
    {
        static string[] unitsMap = new[] { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
        static string[] tensMap = new[] { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

        static string Letterize(int number)
        {
            string numberWords = string.Empty;
            if (number < -99) return "minus " + Letterize(Math.Abs(number));
            if (number / 100 > 0)
            {
                numberWords += Letterize(number / 100) + "-hundred";
                number %= 100;
            }
            
            if (number > 0)
            {
                if (numberWords != "")
                {
                    numberWords += " and ";
                }
                if (number < 20)
                {
                    numberWords += unitsMap[number];
                }
                else
                {
                    numberWords += tensMap[number / 10];
                    if (number % 10 > 0)
                    {
                        numberWords += " " + unitsMap[number % 10];
                    }
                }
            }
            else
            {
                
            }
            return numberWords;
        }
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            for (int i = 0; i < n; i++)
            {
                int num = int.Parse(Console.ReadLine());
                if (Math.Abs(num) > 99 && Math.Abs(num) < 1000)
                {
                    Console.WriteLine(Letterize(num));
                }
                else
                {
                    if (num < -999)
                        Console.WriteLine("too small");
                    else if (num > 999)
                        Console.WriteLine("too large");
                }
            }           
        }
    }
}
