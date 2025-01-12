import React, { useEffect, useState } from "react";
import { request } from "../api";
import { StandardTable } from "erp-hust/lib/StandardTable";
import IconButton from "@mui/material/IconButton";
import EditIcon from "@mui/icons-material/Edit";
import DeleteIcon from "@mui/icons-material/Delete";
import AddIcon from "@mui/icons-material/Add";
import { Modal, Box, Button, TextField } from "@mui/material";

function Customer() {
  const [users, setUsers] = useState([]);
  const [open, setOpen] = useState(false);
  const [newUser, setNewUser] = useState({
    name: "",
    email: "",
    phoneNumber: "",
    avatarUrl: null,
    bod: "",
    isOwner: false,
    status: "active",
    password: "",
  });

  useEffect(() => {
    request("get", "/customer/get-all-data", (res) => {
      setUsers(res.data);
    }).then();
  }, []);

  const columns = [
    {
      title: "Id",
      field: "id",
    },
    {
      title: "Name",
      field: "name",
    },
    {
      title: "Email",
      field: "email",
    },
    {
      title: "Phone",
      field: "phoneNumber",
    },
    {
      title: "Birthday",
      field: "bod",
    },
    {
      title: "Status",
      field: "status",
    },
    {
      title: "Owner",
      field: "owner",
    },
    {
      title: "Edit",
      sorting: false,
      render: (rowData) => (
        <IconButton onClick={() => demoFunction(rowData)} color="success">
          <EditIcon />
        </IconButton>
      ),
    },
    {
      title: "Delete",
      sorting: false,
      render: (rowData) => (
        <IconButton onClick={() => demoFunction(rowData)} color="error">
          <DeleteIcon />
        </IconButton>
      ),
    },
  ];

  const demoFunction = (user) => {
    alert("You clicked on User: " + user.id);
  };

  const handleOpenModal = () => {
    setOpen(true);
  };

  const handleCloseModal = () => {
    setOpen(false);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setNewUser((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = () => {
    console.log(newUser);
    request("post", "http://localhost:8080/api/customer/add", (res) => {
        console.log(res.data);
      alert("User added successfully!");
      setUsers([...users, res.data]);
      setOpen(false);
    }, newUser);
  };

  return (
    <div>
      <Button
        variant="contained"
        color="primary"
        onClick={handleOpenModal}
        startIcon={<AddIcon />}
      >
        Add Customer
      </Button>

      <StandardTable
        title="Customer List"
        columns={columns}
        data={users}
        options={{
          selection: false,
          pageSize: 5,
          search: true,
          sorting: true,
        }}
      />

      <Modal open={open} onClose={handleCloseModal}>
        <Box
          sx={{
            position: "absolute",
            top: "50%",
            left: "50%",
            transform: "translate(-50%, -50%)",
            width: 400,
            bgcolor: "background.paper",
            border: "2px solid #000",
            boxShadow: 24,
            p: 4,
          }}
        >
          <h2>Add New Customer</h2>
          <TextField
            fullWidth
            label="Name"
            name="name"
            value={newUser.name}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            fullWidth
            label="Email"
            name="email"
            value={newUser.email}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            fullWidth
            label="Phone Number"
            name="phoneNumber"
            value={newUser.phoneNumber}
            onChange={handleChange}
            margin="normal"
          />
          <TextField
            fullWidth
            label="Birthday"
            name="bod"
            type="date"
            value={newUser.bod}
            onChange={handleChange}
            margin="normal"
            InputLabelProps={{
              shrink: true,
            }}
          />
          <TextField
            fullWidth
            label="Password"
            name="password"
            value={newUser.password}
            onChange={handleChange}
            margin="normal"
            type="password"
          />
          <Button
            variant="contained"
            color="primary"
            onClick={handleSubmit}
            sx={{ mt: 2 }}
          >
            Submit
          </Button>
        </Box>
      </Modal>
    </div>
  );
}

export default Customer;