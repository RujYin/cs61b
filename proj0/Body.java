public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Body(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(){
		xxPos = 0;
		yyPos = 0;
		xxVel = 0;
		yyVel = 0;
		mass = 0;
		imgFileName = "";
	}
	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double r;
		double dxx = xxPos - b.xxPos;
		double dyy = yyPos - b.yyPos;
		r = Math.pow(dxx*dxx + dyy*dyy,0.5);
		return r;
	}

	public double calcForceExertedBy(Body b){
		double force = G * b.mass*mass/Math.pow(this.calcDistance(b),2);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double forceX;
		double dxx = b.xxPos - xxPos;
		double dyy = b.yyPos - yyPos;
		forceX = this.calcForceExertedBy(b)*dxx/this.calcDistance(b);
		return forceX;
	}

	public double calcForceExertedByY(Body b){
		double forceY;
		double dyy = b.yyPos - yyPos;
		forceY = this.calcForceExertedBy(b)*dyy/this.calcDistance(b);
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] bList){
		double forceX = 0;
		for(int i = 0; i<bList.length;i++){
			if (!(bList[i].equals(this))){
				forceX += this.calcForceExertedByX(bList[i]);
			}
		}
		return forceX;	
	}

	public double calcNetForceExertedByY(Body[] bList){
		double forceY = 0;
		for(int i = 0; i<bList.length;i++){
			if (!(bList[i].equals(this))){
				forceY += this.calcForceExertedByY(bList[i]);
			}
		}
		return forceY;	
	}

	public void update(double dt, double fX, double fY){
		double aX = fX/mass;
		double aY = fY/mass;
		xxVel += aX*dt;
		yyVel += aY*dt;
		xxPos += xxVel*dt;
		yyPos += yyVel*dt; 
	}

	public void draw(){
		String fullRelativePath = "images/" + imgFileName;
		StdDraw.picture(xxPos,yyPos,fullRelativePath);
		//StdDraw.show();
	}




}