<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!-- This file has been downloaded from Bootsnipp.com. Enjoy! -->
    <title></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
  <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>  
    <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
     <style type="text/css">
        /* BOOTSTRAP 3.x GLOBAL STYLES
-------------------------------------------------- */
body {
  padding-bottom: 40px;
  color: #5a5a5a;
}



/* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */

/* Special class on .container surrounding .navbar, used for positioning it into place. */
.navbar-wrapper {
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  z-index: 10;
}



/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
  margin-bottom: 60px;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
  z-index: 1;
}

/* Declare heights because of positioning of img element */
.carousel .item {
  height: 400px;
  background-color:#555;
}
.carousel img {
  position: absolute;
  top: 0;
  left: 0;
  min-height: 400px;
}



/* MARKETING CONTENT
-------------------------------------------------- */

/* Pad the edges of the mobile views a bit */
.marketing {
  padding-left: 15px;
  padding-right: 15px;
}

/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
  text-align: center;
  margin-bottom: 20px;
}
.marketing h2 {
  font-weight: normal;
}
.marketing .col-lg-4 p {
  margin-left: 10px;
  margin-right: 10px;
}


/* Featurettes
------------------------- */

.featurette-divider {
  margin: 80px 0; /* Space out the Bootstrap <hr> more */
}
.featurette {
  padding-top: 120px; /* Vertically center images part 1: add padding above and below text. */
  overflow: hidden; /* Vertically center images part 2: clear their floats. */
}
.featurette-image {
  margin-top: -120px; /* Vertically center images part 3: negative margin up the image the same amount of the padding to center it. */
}

/* Give some space on the sides of the floated elements so text doesn't run right into it. */
.featurette-image.pull-left {
  margin-right: 40px;
}
.featurette-image.pull-right {
  margin-left: 40px;
}

/* Thin out the marketing headings */
.featurette-heading {
  font-size: 50px;
  font-weight: 300;
  line-height: 1;
  letter-spacing: -1px;
}

.col-md-4 text-center.image {
  border: 1px solid #FFFFFF;
  height: 80px;
  width:  80px;
}

/* RESPONSIVE CSS
-------------------------------------------------- */

@media (min-width: 768px) {

  /* Remve the edge padding needed for mobile */
  .marketing {
    padding-left: 0;
    padding-right: 0;
  }

  /* Navbar positioning foo */
  .navbar-wrapper {
    margin-top: 20px;
    margin-bottom: -90px; /* Negative margin to pull up carousel. 90px is roughly margins and height of navbar. */
  }
  /* The navbar becomes detached from the top, so we round the corners */
  .navbar-wrapper .navbar {
    border-radius: 4px;
  }

  /* Bump up size of carousel content */
  .carousel-caption p {
    margin-bottom: 20px;
    font-size: 21px;
    line-height: 1.4;
  }
  footer {
  background: #434343;
  font-size:  0.8em;
  height:     200px;
  position:   relative;
}

footer p {
  color: #FFFFFF;
  text-align: center;
}

footer p.title {
  font-size: 1.2em;
  padding:   15px;
}

footer ul {
  align-items: center;
  display:     flex;
  padding:     20px;
}

footer ul li {
  flex: 1;
}


}


    </style> 
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,700' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="navbar-wrapper">
  <div class="container">
    <div class="navbar navbar-inverse navbar-static-top">
      
        <div class="navbar-header">
      <a class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </a>
        <a class="navbar-brand" href="#">Welcome </a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#footer">Contact</a></li>
            <li><a href="signin.htm">SignIn</a></li>
             <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">SignUp<b class="caret"></b></a>
              <ul class="dropdown-menu">
                <li><a href="createPatient.htm">Patient</a></li>
                <li><a href="createDoctor.htm">Doctor</a></li>
                <li><a href="createScientist.htm">Scientist</a></li>
                <li><a href="createDrugManufactor.htm">DrugManufactor</a></li>
                <li><a href="createCompoundManufactor.htm">CompoundManufactor</a></li>
              </ul>
            </li>
          </ul>
        </div>

    </div>
  </div><!-- /container -->
</div><!-- /navbar wrapper -->

<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="item active">
       <img src="http://thehealthinsider.org/wp-content/uploads/2017/03/CDI.jpg" class="img-responsive">
      <div class="container">
        <div class="carousel-caption">
          <h1>Welcome To Our Mordern Hospital</h1>
        </div>
      </div>
    </div>
    <div class="item">
      <img src="https://observator.com/media/thumbnails/1300x650/Header-Hospital-Air2.jpg" class="img-responsive">
      <div class="container">
        <div class="carousel-caption">
          <h1>What We Do</h1>
          <p>Provide You Personalized health service.</p>
        </div>
      </div>
    </div>
    <div class="item">
      <img src="http://www.isano.co.uk/wp-content/uploads/2016/09/Great-Dental-Care-and-Tooth-Replacement-Services.jpg" class="img-responsive">
      <div class="container">
        <div class="carousel-caption">
          <h1>What You Can Get</h1>
          <p> Receive the long term personal health service based on your health history records</p>
        </div>
      </div>
    </div>
  </div>
  <!-- Controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="icon-prev"></span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="icon-next"></span>
  </a>  
</div>
<!-- /.carousel -->


<!-- Marketing messaging and featurettes
================================================== -->
<!-- Wrap the rest of the page in another container to center all the content. --><body>
   <div align=center>
  <h2>Please Register As A New Scientist</h2>

  <form:form action="addScientist.htm" commandName="scientist"
    method="post">

    <table>
      <tr style="height:50px">
      
        <td><form:input path="firstname" size="30" placeholder="FirstName" style="height:30px; border-radius:5px" required="required" />
          <font color="red"><form:errors path="firstname" /></font></td>
      </tr>

      <tr style="height:50px">
        
        <td><form:input path="lastname" size="30" placeholder="LastName" style="height:30px; border-radius:5px" required="required" />
          <font color="red"><form:errors path="lastname" /></font></td>
      </tr>


      <tr style="height:50px">
        
        <td><form:input id="username" name="username" path="username" size="30" placeholder="UserName" style="height:30px; border-radius:5px" required="required" /><div id="Message"></div>
          <font color="red"><form:errors path="username" /></font></td>
      </tr>

      <tr style="height:50px">
      
        <td><form:input path="email.emailAddress" size="30" placeholder="Email" style="height:30px; border-radius:5px"
            type="email" required="required" /> <font color="red"><form:errors
              path="email.emailAddress" /></font></td>
      </tr>

            <tr style="height:50px">
      
        <td><form:password path="password" size="30" placeholder="Password" style="height:30px; border-radius:5px"
            required="required" /> <font color="red"><form:errors
              path="password" /></font></td>
      </tr>
            
      <tr style="height:50px">
        <td id="td" colspan="2"><input id="submit" type="submit" value="Register User" /></td>
      </tr>
    </table>

  </form:form>
     </div>

<br></br>
<br></br>

 <div>
 <footer id="footer">
    <p class="title">What We Do</p>
    <p>"Help you maintain in good health."</p>
    <ul>
      <li>
        <p><i class="fa fa-map-o fa-2x"></i></p>
        <p>Northeastern University, Boston, MA</p>
      </li>
      <li>
        <p><i class="fa fa-envelope-o fa-2x"></i></p>
        <p>info@husky.neu.edu</p>
      </li>
      <li>
        <p><i class="fa fa-phone fa-2x"></i></p>
        <p>+1 012 345 6789</p>
      </li>
    </ul>
  </footer>
  </div>
</body>
<script>
$(document).ready(function() {
	$("#username").keyup(function() {
		var username=document.getElementById("username").value;
		$.ajax({
			type : "POST",
			contentType : "application/json; charset=utf-8",
			data:username,
			url : "LoginCheck.htm",
			complete : function(data) {				
			
				var dataJson = data.responseText;
				
				var jsonResponse = JSON.parse(dataJson);
				
				
				if (jsonResponse.isCorrectUserAccount==false) {
			
					$("#Message").html("UserName Valid");
					$("#submit").prop("disabled",false);
				}
				else
					{
			
					$("#Message").html("UserName Already Exist !");
					$("#submit").prop("disabled",true);
					}
			}
		}

		)
	});
});
</script>
</html>