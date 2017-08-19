using System;

namespace p08_CaloriesCounter
{
    class CaloriesCounter
    {
        static void Main(string[] args)
        {
            int calories = 0;
            int count = int.Parse(Console.ReadLine());
            string input = "";

            for (int i = 0; i < count; i++)
            {
                input = Console.ReadLine().ToLower();
                switch (input)
                {
                    case "cheese": calories += 500; break;
                    case "tomato sauce": calories += 150; break;
                    case "salami": calories += 600; break;
                    case "pepper": calories += 50; break;
                    default: break;
                }
            }

            Console.WriteLine($"Total calories: {calories}");
        }
    }
}
