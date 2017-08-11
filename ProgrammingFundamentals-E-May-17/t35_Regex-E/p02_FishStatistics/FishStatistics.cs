using System;
using System.Text.RegularExpressions;

namespace p02_FishStatistics
{
    class FishStatistics
    {
        static void Main(string[] args)
        {
            string pattern = @"((?<tail>\>*)\<(?<body>\(+)(?<status>x|-|')>)+";
            MatchCollection matches = Regex.Matches(Console.ReadLine(), pattern);
            byte count = 0;
            foreach (Match match in matches)
            {
                Console.WriteLine("Fish {0}: {1}", 
                    ++count, match.Groups[1].Value.ToString());
                int tailLen = match.Groups["tail"].Value.Length * 2;
                string tailType;
                switch (tailLen)
                {
                    case 0: tailType = "None"; break;
                    case 2: tailType = "Short" + " (" + tailLen + " cm)"; break;
                    default: tailType = ((tailLen > 10) ? "Long" : "Medium") + " (" + tailLen + " cm)";
                        break;
                }
                Console.WriteLine("  Tail type: {0}",tailType);
                int bodyLen = match.Groups["body"].Value.Length * 2;
                string bodyType;
                if (bodyLen > 20) bodyType = "Long";
                else if (bodyLen > 10) bodyType = "Medium";
                else bodyType = "Short";
                Console.WriteLine("  Body type: {0} ({1} cm)", bodyType, bodyLen);
                string status = "";
                switch (match.Groups["status"].Value)
                {
                    case "'": status = "Awake"; break;
                    case "-": status = "Asleep"; break;
                    case "x": status = "Dead"; break;
                }
                Console.WriteLine("  Status: {0}", status);
            }
            if (count < 1) Console.WriteLine("No fish found.");
        }
    }
}
