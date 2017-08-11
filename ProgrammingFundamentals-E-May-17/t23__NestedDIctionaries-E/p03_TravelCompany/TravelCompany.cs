using System;
using System.Collections.Generic;

namespace p03_TravelCompany
{
    class TravelCompany
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, uint>> citiesTransport = new Dictionary<string, Dictionary<string, uint>>();
            string input = Console.ReadLine();
            while (!"ready".Equals(input))
            {
                string[] dest = input.Split(new char[] { ':' }, StringSplitOptions.RemoveEmptyEntries);
                string city = dest[0];
                string[] transport = dest[1].Split(new char[] { ',', '-' }, StringSplitOptions.RemoveEmptyEntries);

                if (!citiesTransport.ContainsKey(city))
                {
                    citiesTransport.Add(city, new Dictionary<string, uint>());
                    citiesTransport[city].Add("_summary", 0);
                }
                for (byte i = 0; i < (byte)transport.Length; i += 2)
                {
                    if (!citiesTransport[city].ContainsKey(transport[i]))
                    {
                        citiesTransport[city].Add(transport[i], uint.Parse(transport[i + 1]));
                    }
                    else
                    {
                        citiesTransport[city][transport[i]] = uint.Parse(transport[i + 1]);
                    }
                }

                input = Console.ReadLine();
            }

            foreach (string city in citiesTransport.Keys)
            {
                uint sum = 0;
                foreach (string vehicle in citiesTransport[city].Keys)
                {
                    if (!vehicle.Equals("_summary"))
                    {
                        sum += citiesTransport[city][vehicle];
                    }
                }
                citiesTransport[city]["_summary"] = sum;
                // Console.WriteLine("{0} {1}", city, citiesTransport[city]["_summary"]);
            }


            input = Console.ReadLine();
            List<string> output = new List<string>();
            while (!"travel time!".Equals(input))
            {
                string city = input.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)[0];
                uint passengers = uint.Parse(input.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries)[1]);
                if (passengers > citiesTransport[city]["_summary"])
                {
                    output.Add(string.Format("{0} -> all except {1} accommodated", city, passengers - citiesTransport[city]["_summary"]));
                }
                else
                {
                    output.Add(string.Format("{0} -> all {1} accommodated", city, passengers));
                }

                input = Console.ReadLine();
            }
            foreach (string res in output)
            {
                Console.WriteLine(res);
            }
        }
    }
}
