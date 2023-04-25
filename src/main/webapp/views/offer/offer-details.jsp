<%@ page import="contracts.models.OfferResponse" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    OfferResponse offer = (OfferResponse) request.getAttribute("offer");
%>
<!DOCTYPE html>
<html lang="uk">
<link rel="stylesheet" href="static/styles/offer-details.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet">
<head>
    <%@include file="../layouts/head.jsp"%>
</head>
<body>
<header>
    <%@include file="../layouts/nav.jsp"%>
</header>
<main class="">
    <div class="details-container">
        <div id="carouselExampleIndicators" class="carousel slide w-50" style="width: fit-content">
            <div class="carousel-indicators">
                <c:forEach var="url" items="${offer.photos}" varStatus="loop">
                    <c:choose>
                        <c:when test="${loop.index == 0}">
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${loop.index}" class="active" aria-current="true" aria-label="Slide ${loop.index}"></button>
                        </c:when>
                        <c:otherwise>
                            <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${loop.index}" aria-label="Slide ${loop.index}"></button>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="carousel-inner">
                <c:forEach var="url" items="${offer.photos}" varStatus="loop">
                    <c:choose>
                    <c:when test="${loop.index == 0}">
                        <div class="carousel-item active">
                            <img src="${url}" class="w-100" style="height: 600px" alt="...">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item">
                            <img src="${url}" class="w-100" style="height: 600px" alt="...">
                        </div>
                    </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>

        <div class="w-50 p-2 d-flex flex-column">
            <div class="d-flex justify-content-between w-100 align-items-center">
                <div class="fs-1"><%=offer.offer.title%></div>
                <div>
                    <div class="fs-3">Price:</div>
                    <div><%=offer.offer.price%>$/month</div>
                </div>
            </div>
            <hr/>
            <div class="mb-2 d-flex justify-content-between p-3">
                <div>
                    <div class="mb-2">Square:</div>
                    <div class="d-flex align-items-center">
                        <div class="me-2">
                            <img src="static/img/area.png" class="icon">
                        </div>
                        <div>
                            <%=offer.offer.square%>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="mb-2">Location:</div>
                    <div class="d-flex align-items-center mb-2">
                        <div class="me-2">
                            <img src="static/img/location.png" class="icon">
                        </div>
                        <div>
                            <%=offer.offer.city%>, <%=offer.offer.country%>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mb-2 ps-2">Balcony:</div>
            <div class="d-flex align-items-center mb-2 ps-2">
                <div class="me-2">
                    <img src="static/img/balcony.png" class="icon">
                </div>
                <div>
                    <c:choose>
                        <c:when test="${offer.offer.balcony}">
                            <div>Yes</div>
                        </c:when>
                        <c:otherwise>
                            <div>No</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <hr/>
            <div>
                <div><%=offer.offer.description%></div>
            </div>
            <div class="d-flex justify-content-center align-self-end mt-2">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#sendModal">
                    Connect us
                </button>

                <div class="modal fade" id="sendModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">Send request form</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="Email" id="requestForm">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="name" name="name" required>
                                        <label for="name">Name</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="surname" name="surname" required>
                                        <label for="surname">Surname</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="email" class="form-control" id="email" name="email" required>
                                        <label for="email">Your email address</label>
                                    </div>
                                    <div class="form-floating">
                                        <textarea id="message" class="form-control" aria-label="message" name="message" required>
                                        </textarea>
                                        <label for="message">Your message</label>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" form="requestForm">Save changes</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<footer class="text-center">
    <%@include file="../layouts/footer.jsp"%>
</footer>
</body>
</html>
