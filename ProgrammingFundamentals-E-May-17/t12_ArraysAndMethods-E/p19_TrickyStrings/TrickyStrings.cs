using System;
namespace p19_TrickyStrings
{
    class TrickyStrings
    {
        static void Main(string[] args)
        {
            var delimiter = Console.ReadLine();
            var numbersOfStrings = int.Parse(Console.ReadLine());
            var result = string.Empty;
            for (int i = 0; i < numbersOfStrings - 1; i++)
            {
                result += Console.ReadLine() + delimiter;
            }
            Console.WriteLine(result + Console.ReadLine());
        }
    }
}
