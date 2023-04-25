<%@ page import="contracts.models.OfferResponse" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
    List<OfferResponse> offers = new ArrayList<>();
    if(request.getAttribute("offers")!= null){
        offers = (ArrayList<OfferResponse>)request.getAttribute("offers");
    }
%>
<link rel="stylesheet" href="static/styles/offers.css">
<div class="offers-container" id="details-close">
    <% for (OfferResponse offer : offers) { %>
    <div class="card" id="<%=offer.getOffer().id%>">
        <div class="row g-0 p-3">
            <div class="col-md-6">
                <img src="<%=offer.getPhotos().size() > 0 ? offer.getPhotos().get(0) : "./static/img/no-photos.png"%>" class="img-fluid rounded-start image-container" alt="...">
            </div>
            <div class="col-md-6">
                <div class="card-body d-flex flex-column">
                    <div class="mb-auto">
                        <h5 class="card-title"><%=offer.offer.title%></h5>
                        <p class="card-text">$
                            <span class="offer-price">
                                <%=offer.offer.price%>
                            </span>/month
                        </p>
                    </div>
                    <div class="mt-auto">
                        <p><%=offer.offer.city%>, <%=offer.offer.country%></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>

<script>
    const cards = document.querySelectorAll(".card");
    const cardDetails = document.getElementById("details-open");
    console.log(cardDetails);
    const cardDetailsClose = document.getElementById("details-close");
    console.log(cardDetailsClose);

    cards.forEach(card => {
        card.addEventListener("click", async function (event) {
            event.stopPropagation()
            console.log(event.currentTarget)
            console.log(event.currentTarget.getAttribute("id"));
            const id = event.currentTarget.getAttribute("id");
            window.location = './Offer?id=' + id;
        })
    })
</script>

