using System;

namespace p08_StringEncryption
{
    class StringEncryption
    {
        static void Main(string[] args)
        {
            int num = int.Parse(Console.ReadLine());
            string output = "";
            for (int i = 0; i < num; i++)
            {
                output += Encrypt(Console.ReadLine()[0]);
            }
            Console.WriteLine(output);
        }

        private static string Encrypt(char letter)
        {
            string encrypted = string.Empty;
            byte fDig = 0;
            byte lDig = 0;
            if ((int)letter / 100 > 0)
            {
                fDig = (byte)(letter / 100);
            }
            else if ((int)letter / 10 > 0)
            {
                fDig = (byte)(letter / 10);
            }
            lDig = (byte)(letter % 10);
            encrypted = fDig.ToString() + lDig.ToString();
            encrypted = (char)(letter + lDig) + encrypted + (char)(letter - fDig);

            return encrypted;
        }
    }
}
