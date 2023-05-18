<%@ page import="contracts.models.OfferResponse" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.EnumSet" %>
<%@ page import="database.models.HouseType" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%
    List<OfferResponse> offers = new ArrayList<>();
    if(request.getAttribute("offers")!= null){
        offers = (ArrayList<OfferResponse>)request.getAttribute("offers");
    }
    List<Enum> houseTypes = new ArrayList<Enum>(EnumSet.allOf(HouseType.class));
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <%@include file="../../views/layouts/head.jsp"%>
</head>
<body>
<header>
    <%@include file="../../views/layouts/nav.jsp"%>
</header>
<link rel="stylesheet" href="static/styles/admin.css">
    <div>
        <div>
            <div class="m-3">
                <form class="card w-100 p-3" method="post" action="Admin?offerAction=add" enctype="multipart/form-data">
                    <div class="row g-0">
                        <div class="col-md-8">
                            <div class="card-body d-flex justify-content-between">
                                <div class="w-50">
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="titleNew" name="title" required>
                                        <label for="titleNew">Заголовок</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="countryNew" name="country" required>
                                        <label for="countryNew">Країна</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="text" class="form-control" id="cityNew" name="city" required>
                                        <label for="cityNew">Місто</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input type="number" step="any" class="form-control" id="squareNew" name="square" required>
                                        <label for="squareNew">Площа</label>
                                    </div>
                                    <div class="form-floating">
                                        <textarea id="descriptionNew" class="form-control" aria-label="Description" name="description" required></textarea>
                                        <label for="descriptionNew">Опис</label>
                                    </div>
                                </div>
                                <div class="mb-3 ms-2">
                                    <label for="uploadPhotosNew" class="form-label">Завантажити фотографії</label>
                                    <input class="form-control" type="file" id="uploadPhotosNew" name="uploadPhotos" multiple>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="d-flex align-items-center mb-3 down-panel flex-wrap">
                        <select class="form-select" aria-label="Default select example" name="type">
                            <% for (Enum houseType : houseTypes) { %>
                                <c:set var="htype" value="<%=houseType%>"/>
                                <option value="<%=houseType%>"><%=houseType%></option>
                            <% } %>
                        </select>
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="balconyNew" name="balcony" value="<%=true%>">
                            <label class="form-check-label" for="balconyNew">Балкон</label>
                        </div>
                        <div class="form-floating ms-auto price-cont">
                            <input type="number" step="any" class="form-control" id="priceNew" name="price" value="0" required>
                            <label for="priceNew">Ціна</label>
                        </div>
                    </div>
                    <div class="">
                        <button type="submit" class="btn btn-primary">Примінити зміни</button>
                    </div>
                </form>
            </div>
        </div>
        <% for (OfferResponse offer : offers) { %>
        <div class="m-3">
            <form class="card w-100 p-3" method="post" action="Admin?offerAction=update" enctype="multipart/form-data">
                <input type="hidden" id="<%=offer.offer.id%>" name="id" value="<%=offer.offer.id%>">
                <div class="row g-0">
                    <div class="col-md-4 img-box">
                        <img src="<%=offer.getPhotos().size() > 0 ? offer.getPhotos().get(0) : "./static/img/no-photos.png"%>" class="img-fluid rounded-start" alt="...">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body d-flex justify-content-between">
                            <div class="w-50">
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="title" name="title" value="<%=offer.offer.title%>" required>
                                    <label for="title">Заголовок</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="country" name="country" value="<%=offer.offer.country%>" required>
                                    <label for="country">Країна</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="city" name="city" value="<%=offer.offer.city%>" required>
                                    <label for="city">Місто</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="number" step="any" class="form-control" id="square" name="square" value="<%=offer.offer.square%>" required>
                                    <label for="square">Площа</label>
                                </div>
                                <div class="form-floating">
                                    <textarea id="description" class="form-control" aria-label="Description" name="description" required>
                                        <%=offer.offer.description%>
                                    </textarea>
                                    <label for="description">Опис</label>
                                </div>
                            </div>
                            <div class="w-50">
                                <ul class="list-group ms-3 mb-3">
                                    <% for (String photo : offer.getPhotos()) { %>
                                        <li class="list-group-item">
                                            <input class="form-check-input me-1" type="checkbox" name="photo" value="<%=photo%>" id="photo_<%=offer.offer.id%>" checked>
                                            <input type="hidden" value="<%=photo%>" name="oldPhoto">
                                            <label class="form-check-label" for="photo_<%=offer.offer.id%>"><%=photo%></label>
                                        </li>
                                    <% } %>
                                </ul>
                                <div class="mb-3 ms-3">
                                    <label for="uploadPhotos" class="form-label">Завантажити фотографії</label>
                                    <input class="form-control" type="file" id="uploadPhotos" name="uploadPhotos" multiple>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="d-flex align-items-center mb-3 down-panel flex-wrap">
                    <select class="form-select" aria-label="Default select example" name="type">
                        <% for (Enum houseType : houseTypes) { %>
                            <c:set var="htype" value="<%=houseType%>"/>
                            <c:set var="etype" value="<%=offer.offer.type%>"/>s
                            <c:if test="${htype == etype}">
                                <option selected value="<%=houseType%>"><%=houseType%></option>
                            </c:if>
                            <c:if test="${htype != etype}">
                                <option value="<%=houseType%>"><%=houseType%></option>
                            </c:if>
                        <% } %>
                    </select>
                    <div class="form-check form-switch">
                        <c:set var="balcony" value="<%=offer.offer.balcony%>"/>
                        <c:if test="${balcony}">
                            <input class="form-check-input" type="checkbox" role="switch" id="balcony" name="balcony" checked value="<%=!offer.offer.balcony%>">
                        </c:if>
                        <c:if test="${!balcony}">
                            <input class="form-check-input" type="checkbox" role="switch" id="balcony" name="balcony" value="<%=!offer.offer.balcony%>">
                        </c:if>
                        <label class="form-check-label" for="balcony">Балкон</label>
                    </div>
                    <div class="form-floating ms-auto price-cont">
                        <input type="number" step="any" class="form-control" id="price" name="price" value="<%=offer.offer.price%>" required>
                        <label for="price">Ціна</label>
                    </div>
                </div>
                <div class="d-flex">
                    <button type="submit" value="apply" name="itemAction" class="btn btn-primary me-3">Примінити зміни</button>
                    <button type="submit" value="delete" name="itemAction" class="btn btn-danger">Видалити пропозицію</button>
                </div>
            </form>
        </div>
        <%}%>
    </div>
<main>

</main>
<footer class="text-center">
    <%@include file="../../views/layouts/footer.jsp"%>
</footer>
</body>
</html>
