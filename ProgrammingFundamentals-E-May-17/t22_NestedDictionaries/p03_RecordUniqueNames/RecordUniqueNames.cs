using System;
using System.Collections.Generic;

namespace p03_RecordUniqueNames
{
    class RecordUniqueNames
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            HashSet<string> setNames = new HashSet<string>();
            for (int i = 0; i < n; i++)
            {
                setNames.Add(Console.ReadLine());
            }
            foreach (string cName in setNames)
            {
                Console.WriteLine(cName);
            }
        }
    }
}
