import {
  Admin,
} from "react-admin";
import {
  amplicodeDarkTheme,
  amplicodeLightTheme,
} from "./themes/amplicodeTheme/amplicodeTheme";
import { dataProvider } from "./dataProvider";
import { Resource, ListGuesser, EditGuesser, ShowGuesser } from "react-admin";
import { ProductTypesList } from "./resources/productTypes/ProductTypesList";
import { StocksList } from "./resources/stocks/StocksList";

export const App = () => {
  return (
    <Admin
      dataProvider={dataProvider}
      lightTheme={amplicodeLightTheme}
      darkTheme={amplicodeDarkTheme}
    >

    <Resource name="colors" 
        list={ListGuesser} edit={EditGuesser} show={ShowGuesser}
        
        recordRepresentation="name"
      />

    <Resource name="productTypes" 
options={{label: "Product Types"}}
        list={ProductTypesList} edit={EditGuesser} show={ShowGuesser}
        
        recordRepresentation="name"
      />

    <Resource name="stocks" 
        list={StocksList} edit={EditGuesser} show={ShowGuesser}
        
        recordRepresentation="productTypeName"
      />
</Admin>
  )
};
