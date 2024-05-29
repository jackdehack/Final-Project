public class Vector {
   public double x; // x component of the vector
   public double y; // y component of the vector

   // constructor that sets the x and y components
   public Vector(double x, double y) {
       this.x = x;
       this.y = y;
   }

   // method to calculate the magnitude (length) of the vector
   public double magnitude() {
       return Math.sqrt(x * x + y * y); // use pythagorean theorem
   }

   // method to normalize the vector (make it a unit vector)
   public Vector normalize() {
       double magnitude = magnitude(); // get the magnitude
       return new Vector(x / magnitude, y / magnitude); // divide x and y by the magnitude
   }

   // method to add this vector to another vector
   public Vector add(Vector other) {
       return new Vector(x + other.x, y + other.y); // add x and y components
   }

   // method to multiply this vector by a scalar (number)
   public Vector multiply(double factor) {
       return new Vector(x * factor, y * factor); // multiply x and y by the factor
   }

   // method to subtract another vector from this vector
   public Vector subtract(Vector other) {
       return new Vector(x - other.x, y - other.y); // subtract x and y components
   }

   // method to calculate the dot product of this vector and another vector
   public double dotProduct(Vector other) {
       return this.x * other.x + this.y * other.y; // multiply corresponding components and sum them up
   }

   // method to rotate this vector by an angle theta (in radians)
   public Vector rotate(double theta) {
       double cosTheta = Math.cos(theta); // cosine of the angle
       double sinTheta = Math.sin(theta); // sine of the angle
       double newX = x * cosTheta - y * sinTheta; // new x component after rotation
       double newY = x * sinTheta + y * cosTheta; // new y component after rotation
       return new Vector(newX, newY); // return the new rotated vector
   }
}
