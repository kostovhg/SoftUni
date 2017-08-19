using System;
using System.Numerics;

namespace p01_TheaThePhotographer
{
    class Program
    {
        static void Main(string[] args)
        {
            // N is amount of pictures
            ulong N = ulong.Parse(Console.ReadLine());
            // filter time for all pictures
            ulong FT = ulong.Parse(Console.ReadLine());
            // filter factor (%) of all pictures that are for upload
            ulong FF = ulong.Parse(Console.ReadLine());
            // time for filtered picture to be uploaded
            ulong UT = ulong.Parse(Console.ReadLine());
            // pictures to be uploaded
            ulong PU = (ulong)Math.Ceiling((FF / 100.0) * N);
            // Using big integer as if all values are maximum, it will take 2314814:19:33:20
            BigInteger totalSeconds = new BigInteger(N * FT + PU * UT);
            // separate the value greater than a day in secconds
            // to make BigInteger small enought to be casted to ulong
            // withowt losses
            ulong days = (ulong)BigInteger.Divide(totalSeconds, 86400);
            // Remaining seconds to initial variable of type BigInteger
            totalSeconds = BigInteger.Remainder(totalSeconds, 86400);
            // A special class deals with separating the secconds to human readable format
            TimeSpan t = TimeSpan.FromSeconds((ulong)totalSeconds);
            Console.WriteLine($"{days}:{t.Hours:00}:{t.Minutes:00}:{t.Seconds:00}");
        }
    }
}
