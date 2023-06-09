<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<link rel="stylesheet" href="static/styles/main-large-screen.css">
<title>Bootstrap Example</title>
<div>
    <div class="main-img-box">
        <div class="adv-label-container">
            <div class="adv-label">
                <a href="#offers" class="adv-label-inner link-style">Знайдіть житло мрії з нами!</a>
            </div>
        </div>
        <img src="static/img/main-house.jpg" class="center-fit">
    </div>
    <div class="offers-block" id="offers">
        <nav class="navbar navbar-light bg-transparent">
            <div class="container-fluid">
                <a class="navbar-brand text-secondary">Фільтри</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
                    <div class="offcanvas-header">
                        <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">Фільтри</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                    </div>
                    <div class="offcanvas-body">
                        <form class="mt-3" role="search" method="get">
                            <div>Місто:</div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Місто" name="city" aria-label="City" aria-describedby="City">
                            </div>
                            <div>Країна:</div>
                            <div class="input-group mb-3">
                                <input type="text" class="form-control" placeholder="Країна" name="country" aria-label="Country" aria-describedby="Country">
                            </div>
                            <div>Ціна:</div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Мін.:</span>
                                <input type="text" class="form-control" placeholder="0.00" name="minPrice" aria-label="minPrice">
                                <span class="input-group-text">Макс.:</span>
                                <input type="text" class="form-control" placeholder="0.00" name="maxPrice" aria-label="maxPrice">
                            </div>
                            <div>Площа:</div>
                            <div class="input-group mb-3">
                                <span class="input-group-text">Мін.:</span>
                                <input type="text" class="form-control" name="minSquare" placeholder="0" aria-label="minSquare">
                                <span class="input-group-text">Макс.:</span>
                                <input type="text" class="form-control" name="maxSquare" placeholder="0" aria-label="maxSquare">
                            </div>
                            <button class="btn btn-success" type="submit">Шукати</button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
    </div>
    <div>
        <%@include file="offers.jsp"%>
    </div>
</div>

