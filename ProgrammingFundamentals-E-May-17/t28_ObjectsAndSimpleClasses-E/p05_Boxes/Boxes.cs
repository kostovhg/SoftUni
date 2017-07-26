using System;
using System.Collections.Generic;
using System.Linq;

namespace p05_Boxes
{
    class Point
    {
        public int X { get; set; }
        public int Y { get; set; }
        public static double CalculateDistance(Point p1, Point p2)
        {
            int deltaX = p2.X - p1.X;
            int deltaY = p2.Y - p1.Y;
            return Math.Sqrt(deltaX * deltaX + deltaY * deltaY);
        }

        public Point(int x, int y)
        {
            this.X = x;
            this.Y = y;
        }
    }
    class Box
    {
        Point UpperLeft { get; set; }
        Point UpperRight { get; set; }
        Point BottomLeft { get; set; }
        Point BottomRight { get; set; }

        public int Width { get { return (int)Point.CalculateDistance(UpperLeft, UpperRight); } }
        public int Height { get { return (int)Point.CalculateDistance(UpperLeft, BottomLeft); } }
        public static int CalculatePerimeter(int width, int height)
        {
            return (width * 2 + height * 2);
        }
        public static int CalculateArea(int width, int height)
        {
            return (width * height);
        }
        public Box(Point upperLeft, Point upperRight, Point bottomLeft, Point bottomRight )
        {
            this.UpperLeft = upperLeft;
            this.UpperRight = upperRight;
            this.BottomLeft = bottomLeft;
            this.BottomRight = bottomRight;
        }
    }
    class Boxes
    {
        static void Main(string[] args)
        {
            List<Box> boxes = new List<Box>();
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input
                    .Split(new string[] { " | " },
                    StringSplitOptions.RemoveEmptyEntries);
                List<Point> points = new List<Point>();
                foreach (string token in tokens)
                {
                    int[] point = token
                        .Split(':')
                        .Select(int.Parse)
                        .ToArray();
                    points.Add(new Point(point[0], point[1]));
                }
                boxes.Add(new Box(points[0], points[1], points[2], points[3]));
            }
            foreach (Box box in boxes)
            {
                Console.WriteLine("Box: {0}, {1}",
                    box.Width, box.Height);
                Console.WriteLine("Perimeter: {0}",
                    Box.CalculatePerimeter(box.Width, box.Height));
                Console.WriteLine("Area: {0}",
                    Box.CalculateArea(box.Width, box.Height));
            }
        }
    }
}
