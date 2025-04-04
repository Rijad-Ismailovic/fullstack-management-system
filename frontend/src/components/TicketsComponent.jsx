import React, { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router";

import {
  Container,
  Col,
  Row,
  Image,
  Card,
  Button,
  CardBody,
  Stack,
} from "react-bootstrap";
import TicketModal from "./TicketModal";
import { getAllTrips, search } from "../services/TripService";
import { mapToHHMM } from "../utils/timeUtils";

function TicketsComponent({query}) {
  const [data, setData] = useState([])

  useEffect(() => {
    const hasQuery = query.departure || query.arrival || query.departureDate || query.returnDate
    
    if (hasQuery) {
      search(
        query.departure,
        query.arrival,
        query.departureDate,
        query.returnDate
      )
        .then((response) => {
          setData(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    } else {
      getAllTrips()
        .then((response) => {
          setData(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    }
    
  }, [query]);

  function createCard(trip, index) {
    return (
      <Container key={index} className="mb-3 px-0 bg-light">
        <Card className="shadow-sm position-relative">
          <CardBody className="d-flex align-items-start gap-3 bg-light">
            <Col
              xs={2}
              style={{ width: "80px", height: "80px" }}
              className="px-0 rounded overflow-hidden"
            >
              <Image
                src={trip.image}
                fluid
                className="w-100 h-100 object-fit-cover"
                style={{ filter: "blur(0.3px)" }}
              />
            </Col>

            <Col className="flex-grow-1 d-flex flex-column justify-content-between">
              <Card.Title className="mb-2 fs-6">
                {trip.departureLocation}{" "}
                <span className="text-primary">&rarr;</span>{" "}
                {trip.arrivalLocation}
              </Card.Title>
              <Row className="px-0 small text-muted mt-2">
                {/* <p className="mb-0">{mapToHHMM(trip.durationMinutes)}</p> */}
                <p className="mb-0">{trip.departureDate}</p>
                <p className="mb-0">
                  {trip.departureTime.substring(0, 5)} -{" "}
                  {trip.arrivalTime.substring(0, 5)}
                </p>
              </Row>
            </Col>

            <Col className="d-flex flex-column align-items-end mt-auto">
              <p className="mb-1 fw-bold">{trip.price} KM</p>
              <TicketModal />
            </Col>
          </CardBody>
        </Card>
      </Container>
    );
  }

  return (
    <Container>
      {data.map((ticket, index) => createCard(ticket, index))}
    </Container>
  );
}

export default TicketsComponent;
