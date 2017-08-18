using System;

namespace p02_NumberChecker
{
    class NumberChecker
    {
        static void Main(string[] args)
        {
            string sNum = Console.ReadLine();
            long lNum = 0; double dNum = 0.0;
            if (long.TryParse(sNum, out lNum))
                Console.WriteLine("integer");
            else if(double.TryParse(sNum, out dNum))
                Console.WriteLine("floating-point");
            else
                Console.WriteLine("nan");
        }
    }
}
