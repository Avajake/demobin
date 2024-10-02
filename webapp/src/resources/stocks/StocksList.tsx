import { Datagrid, List, TextField, NumberField } from "react-admin";

        

        export const StocksList = () => {
        return (
            <List resource="stocks">
                <Datagrid rowClick="edit">
                    <TextField source="productTypeName"/>
<TextField source="colorName"/>
<NumberField source="quantity"/>
                    
                </Datagrid>
            </List>
            );
        };