
import './App.css';
import React, { Component } from 'react'
import AppNavbar from './AppNavBar';
import { Container,Table } from 'reactstrap';

class App extends Component {
  // Constructor 
  constructor(props) {
    super(props);

    this.state = {
        products: [],
        DataisLoaded: false
    };
  }

  // ComponentDidMount is used to
  // execute the code 
  componentDidMount() {
      fetch("http://localhost:8080/products")
          .then((res) => res.json())
          .then((json) => {
              this.setState({
                products: json,
                DataisLoaded: true
              });
          })
  }
  render() {
    const { DataisLoaded, products } = this.state;
    if (!DataisLoaded) return <div>
          <h1 style={{textAlignVertical: "center",textAlign: "center",}}> Loading... </h1> </div> ;

    const productList = products.map(item => {
      return <tr key={item.id}>
          <td style={{whiteSpace: 'nowrap'}}>{item.id}</td>
          <td>{item.ean}</td>
          <td>{item.weight}</td>
          <td>{item.price}</td>
          <td>{item.name}</td>
          <td>{item.description}</td>
          <td>{item.assembled+""}</td>
          <td>{item.dimension.depth}</td>
          <td>{item.dimension.width}</td>
          <td>{item.dimension.height}</td>
          <td>{item.color}</td>
      </tr>
    });

    return (
      <div>
          <AppNavbar/>
          <Container fluid>
              <h3>Products</h3>
              <Table className="mt-4">
                  <thead>
                  <tr>
                      <th>Id</th>
                      <th>Ean</th>
                      <th>Weight</th>
                      <th>Price</th>
                      <th>Name</th>
                      <th>Description</th>
                      <th>Assembled</th>
                      <th>Depth</th>
                      <th>Width</th>
                      <th>Height</th>
                      <th>Color</th>
                  </tr>
                  </thead>
                  <tbody>
                  {productList}
                  </tbody>
              </Table>
          </Container>
      </div>
    );
      
  }
}

export default App;