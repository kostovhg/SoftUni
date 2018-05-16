function cone(radius, height) {
    let volume = (1 / 3) * Math.PI * (radius ** 2) * height;
    let surfaceArea = Math.PI * radius * (radius + Math.sqrt(radius ** 2 + height ** 2));
    console.log(`volume = ${volume}`);
    console.log(`area = ${surfaceArea}`);
}

cone(3, 5);
cone(3.3, 7.8);