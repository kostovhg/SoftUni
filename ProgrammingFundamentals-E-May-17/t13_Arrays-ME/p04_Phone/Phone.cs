using System;

namespace p04_Phone
{
    class Phone
    {
        static int TelNumDigSum(string sNum)
        {
            byte sum = 0;
            for (int i = 0; i < sNum.Length; i++)
            {
                if ((char)sNum[i] > 48 && (char)sNum[i] < 58)
                {
                    sum += (byte)(sNum[i] - '0');
                }
            }
            return sum;
        }

        static sbyte TelNumDigDif(string sNum)
        {
            sbyte dif = 0;
            bool firstDigit = true;
            for (int i = 0; i < sNum.Length; i++)
            {
                if ((char)sNum[i] > 48 && (char)sNum[i] < 58)
                {
                    if (!firstDigit)
                    {
                        dif -= (sbyte)(sNum[i] - '0');
                    }
                    else
                    {
                        dif += (sbyte)(sNum[i] - '0');
                        firstDigit = false;
                    }
                    
                }
            }
            return dif;
        }
        static void Main(string[] args)
        {
            string[] numbers = Console.ReadLine().Split();
            string[] names = Console.ReadLine().Split();
            string[] action = Console.ReadLine().Split();
            bool inputReqName = true;
             
            while (!action[0].Equals("done"))
            {
                inputReqName = true;
                int index = Array.IndexOf(names, action[1]);
                if (index == -1)
                {
                    index = Array.IndexOf(numbers, action[1]);
                    inputReqName = false;
                }

                if (action[0].Equals("call"))
                {
                    int sum = TelNumDigSum(numbers[index]);
                    Console.WriteLine("calling {0}...", inputReqName ? numbers[index] : names[index]);
                    if (sum % 2 == 0)
                    {
                        int minutes = sum / 60;
                        int seconds = sum % 60;
                        Console.WriteLine("call ended. duration: {0:D2}:{1:D2}", minutes, seconds );
                    }
                    else
                    {
                        Console.WriteLine("no answer");
                    }
                }
                else if (action[0].Equals("message"))
                {
                    int dif = TelNumDigDif(numbers[index]);
                    Console.WriteLine("sending sms to {0}...", inputReqName ? numbers[index] : names[index]);
                    if (dif % 2 == 0)
                    {
                        Console.WriteLine("meet me there");
                    }
                    else
                    {
                        Console.WriteLine("busy");
                    }
                }
                action = Console.ReadLine().Split();
            }
        }
    }
}
