using System;

namespace p03_ExchangeVariableValues
{
    class ExchangeVariableValues
    {
        static void Main(string[] args)
        {
            int varA = int.Parse(Console.ReadLine());
            int varB = int.Parse(Console.ReadLine());
            int varTemp = varA;
            varA = varB;
            varB = varTemp;
            Console.WriteLine(varA);
            Console.WriteLine(varB);
        }
    }
}
