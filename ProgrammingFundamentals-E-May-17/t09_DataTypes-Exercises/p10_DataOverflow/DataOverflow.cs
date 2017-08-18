using System;
namespace p10_DataOverflow
{
    class DataOverflow
    {
        static void Main(string[] args)
        {
            ulong num1 = ulong.Parse(Console.ReadLine());
            ulong num2 = ulong.Parse(Console.ReadLine());
            ulong bigger = Math.Max(num1, num2);
            ulong lower = Math.Min(num1, num2);
            string bigType = "";
            string lowType = "byte";
            ulong lowerMax = 0;
            if (bigger <= byte.MaxValue)
            {
                bigType = "byte";
            }
            else if (bigger <= ushort.MaxValue )
            {
                bigType = "ushort";
            }
            else if (bigger <= uint.MaxValue)
            {
                bigType = "uint";
            }
            else
            {
                bigType = "ulong";
            }
            if (lower <= byte.MaxValue)
            {
                lowType = "byte";
                lowerMax = byte.MaxValue;
            }
            else if (lower <= ushort.MaxValue)
            {
                lowType = "ushort";
                lowerMax = ushort.MaxValue;
            }
            else if (lower <= uint.MaxValue)
            {
                lowType = "uint";
                lowerMax = uint.MaxValue;
            }
            else
            {
                lowType = "ulong";
                lowerMax = ulong.MaxValue;
            }
            Console.WriteLine($"bigger type: {bigType}");
            Console.WriteLine($"smaller type: {lowType}");
            Console.WriteLine($"{bigger} can overflow {lowType} {Math.Round(bigger / (double)lowerMax, 0)} times");
        }
    }
}
