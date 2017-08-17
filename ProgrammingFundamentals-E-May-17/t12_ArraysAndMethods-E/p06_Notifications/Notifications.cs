using System;

namespace p06_Notifications
{
    class Notifications
    {
        static string ShowSuccess(string operation, string message)
        {
            string output = $"Successfully executed {operation}.\r\n" +
                "==============================\r\n" +
                $"Message: {message}.";
            return output;
        }
        static string ShowError(string operation, int code)
        {
            string output = $"Error: Failed to execute {operation}.\r\n" +
                "==============================\r\n" +
                $"Error Code: {code}.\r\n" +
                "Reason: " + ((code >= 0) ? "Invalid Client Data." : "Internal System Failure.");
            return output;
        }
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            for (int i = 0; i < n; i++)
            {
                string result = Console.ReadLine();
                if (result.Equals("success"))
                {
                    string operation = Console.ReadLine();
                    string message = Console.ReadLine();
                    Console.WriteLine(ShowSuccess(operation, message));
                }
                else if (result.Equals("error"))
                {
                    string operation = Console.ReadLine();
                    int code = int.Parse(Console.ReadLine());
                    Console.WriteLine(ShowError(operation, code));
                }
            }
        }
    }
}
