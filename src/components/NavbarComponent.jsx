import React from 'react'
import { Navbar, Container, Nav, Form, Button, Badge } from 'react-bootstrap'

function NavbarComponent() {
  return (
    <Navbar expand="lg" className=" navbar-light bg-light">
      <Container className="px-4 px-lg-5">
        <Navbar.Brand href="/">Global</Navbar.Brand>
        <Navbar.Toggle aria-controls="navbar-nav" />
        <Navbar.Collapse id="navbar-nav">
          <Nav className="me-auto mb-2 mb-lg-0 ms-lg-4">
            <Nav.Link href="#!" active>
              Home
            </Nav.Link>
            <Nav.Link href="#!">About</Nav.Link>
          </Nav>
          <Form className="d-flex">
            <Button variant="outline-dark" type="submit">
              <i className="bi-cart-fill me-1"></i>
              Cart
              <Badge bg="dark" text="white" className="ms-1 rounded-pill">
                0
              </Badge>
            </Button>
          </Form>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
}

export default NavbarComponent