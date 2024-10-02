import { Datagrid, List, NumberField, TextField } from "react-admin";

        

        export const ProductTypesList = () => {
        return (
            <List resource="productTypes">
                <Datagrid rowClick="edit">
<TextField source="name"/>
<TextField source="description"/>
                    
                </Datagrid>
            </List>
            );
        };