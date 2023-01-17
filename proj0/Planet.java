public class Planet {

   
   //current x position
   public double xxPos;
   //current y position
   public double yyPos;
   //current velocity in the x direction
   public double xxVel;
   public double yyVel;
   public double mass;
   public String imgFileName;
   /**good practice to declare any constants 
    * as a ‘static final’ variable in your class, */
   public static final double G = 6.67e-11; 

   /**a constructor - __init__*/
   public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
   }
    /**a constructor: take in a Planet object and
     *  initialize an identical Planet object */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x_d = p.xxPos - xxPos;
        double y_d = p.yyPos - yyPos;
        return Math.sqrt(x_d * x_d + y_d * y_d);
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        double F = G * mass * p.mass / (r*r);
        return F;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        return F * dx / r;

    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        double r = calcDistance(p);
        double F = calcForceExertedBy(p);
        return F * dy / r;

    }
    
    public double calcNetForceExertedByX(Planet[] planets){
        double NetFx = 0;
        for (Planet p : planets) {
            if(this.equals(p)) {
                continue;
            }
            NetFx += calcForceExertedByX(p);
        }

        return NetFx;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double NetFy = 0;
        for (Planet p : planets) {
            if(this.equals(p)) {
                continue;
            }
            NetFy += calcForceExertedByY(p);
        }

        return NetFy;
    }
    

    public void update(double dt,double fX,double fY) {
    /*Calculate the acceleration using the provided x and yforces. */
    double aX = fX / mass;
    double aY = fY / mass;

    /*new velocity by using the acceleration and current velocity. vx+dt⋅ax, vy+dt⋅ay */
    xxVel += aX * dt;
    yyVel += aY * dt;
    
    //new position:   px+dt⋅vx,py+dt⋅vy
    xxPos += dt * xxVel;
    yyPos += dt *yyVel;

    }

    //non-static method 
    public void draw() {
        StdDraw.picture(xxPos, yyPos,imgFileName);
    }

   }

