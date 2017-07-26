using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_HTMLContents
{
    class HTMLContents
    {
        static void Main(string[] args)
        {
            string input;
            List<string> htmlText = new List<string>()
            { "<!DOCTYPE>", "<html>", "<head>", "\t<title></title>", "</head>",
                "<body>", "</body>", "</html>"
            };
            while (!"exit".Equals(input = Console.ReadLine()))
            {
                string tag = input.Split()[0];
                string cont = input.Split()[1];
                if ("title".Equals(tag))
                {
                    htmlText.RemoveAt(3);
                    htmlText.Insert(3, DecodeTag(tag, cont));
                }
                else
                {
                    htmlText.Insert(htmlText.Count - 3, DecodeTag(tag, cont));
                }
            }
            File.WriteAllLines("output.html", htmlText);
        }

        private static string DecodeTag(string tag, string cont)
        {
            return string.Format("\t<{0}>{1}</{0}>", tag, cont);
        }
    }
}
