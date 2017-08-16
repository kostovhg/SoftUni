using System;
using System.Collections.Generic;

namespace p02_TrackDownloader
{
    class TrackDownloader
    {
        static void Main(string[] args)
        {
            List<string> banned = new List<string>(Console.ReadLine().Split());
            string input = Console.ReadLine();
            List<string> output = new List<string>();
            while (!input.Equals("end"))
            {
                bool ok = true;
                foreach (var ban in banned)
                {
                    if (input.Contains(ban))
                    {
                        ok = false;
                        break;
                    }
                }
                if(ok)
                {
                    output.Add(input);
                }
                input = Console.ReadLine();
            }
            output.Sort();
            foreach (var mp3 in output)
            {
                Console.WriteLine(mp3);
            }
        }
    }
}
