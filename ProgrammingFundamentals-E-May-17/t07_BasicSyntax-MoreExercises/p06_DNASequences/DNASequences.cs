using System;

namespace p06_DNASequences
{
    class DNASequences
    {
        static void Main(string[] args)
        {
            int sum = int.Parse(Console.ReadLine());
            char[] bases = { 'A', 'C', 'G', 'T' , 'O' , 'X'};
            int dnas = 0;
            int x = 0;

            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    for (int k = 0; k < 4; k++)
                    {
                        x = (i + j + k + 3 >= sum) ? 0 : 1;
                        Console.Write("{0}{1}{2}{3}{0} ", bases[4 + x], bases[i], bases[j], bases[k]);
                        dnas++;
                        if (dnas % 4 == 0) Console.WriteLine();
                    }
                }
            }
        }
    }
}
