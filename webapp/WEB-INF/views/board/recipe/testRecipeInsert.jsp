<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Recipe Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .section {
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            background-color: #f7f7f7;
        }
        .section h2 {
            margin: 0;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
        }
        .field {
            margin: 10px 0;
            padding: 5px;
        }
        .field label {
            display: block;
            font-weight: bold;
        }
        .field input[type="text"],
        .field input[type="number"],
        .field select {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .field textarea {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .field input[type="file"] {
            padding: 5px;
            margin: 5px 0;
        }
        .section input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <form action="insertRecipe.re" method="post" enctype="multipart/form-data">
            <div class="section">
                <h2>Recipe Title</h2>
                <div class="field">
                    <label for="recipeTitle">Recipe Title:</label>
                    <input type="text" id="recipeTitle" name="recipeTitle">
                </div>
                <div class="field">
                    <label for="recipeCategoryNo">Recipe Category:</label>
                    <select id="recipeCategoryNo" name="recipeCategoryNo">
                        <option value="1">Category 1</option>
                        <option value="2">Category 2</option>
                        <option value="3">Category 3</option>
                        <option value="4">Category 4</option>
                        <option value="5">Category 5</option>
                        <!-- Add more categories as needed -->
                    </select>
                </div>
            </div>

            <div class="section">
                <h2>Recipe Pics (Up to 2)</h2>
                <%
                    for (int i = 0; i < 2; i++) {
                %>
                    <div class="field">
                        <label for="recipeNameOrigin<%=i%>">Recipe Pic Name Upload:</label>
                        <input type="file" id="recipeNameOrigin<%=i%>" name="recipeNameOrigin<%=i%>">
                    </div>
                    <input type="hidden" id="recipePicLev<%=i%>" name="recipePicLev<%=i%>" value="<%=i%>">
                <%
                    }
                %>
            </div>

            <div class="section">
                <h2>Ingredients (Up to 3)</h2>
                <%
                    for (int i = 0; i < 3; i++) {
                %>
                    <div class="field">
                        <label for="ingredient<%=i%>">Ingredient <%=i+1%>:</label>
                        <input type="text" id="ingredient<%=i%>" name="ingredient<%=i%>">
                    </div>
                    <div class="field">
                        <label for="ingredientAmount<%=i%>">Amount <%=i+1%>:</label>
                        <input type="text" id="ingredientAmount<%=i%>" name="ingredientAmount<%=i%>">
                    </div>
                <%
                    }
                %>
            </div>

            <div class="section">
                <h2>Cooking Steps (Up to 2)</h2>
                <%
                    for (int i = 0; i < 2; i++) {
                %>
                    <div class="field">
                        <label for="cookStepsTitle<%=i%>">Step <%=i+1%> Title:</label>
                        <input type="text" id="cookStepsTitle<%=i%>" name="cookStepsTitle<%=i%>">
                    </div>
                    <div class="field">
                        <label for="cookStepsContent<%=i%>">Step <%=i+1%> Content:</label>
                        <textarea id="cookStepsContent<%=i%>" name="cookStepsContent<%=i%>"></textarea>
                    </div>
                <%
                    }
                %>
            </div>

            <div class="section">
                <h2>Tags (Up to 1)</h2>
                <%
                    for (int i = 0; i < 1; i++) {
                %>
                    <div class="field">
                        <label for="tagNo<%=i%>">Tag <%=i+1%>:</label>
                        <input type="text" id="tagNo<%=i%>" name="tagNo<%=i%>">
                    </div>
                <%
                    }
                %>
            </div>

            <div class="section">
                <input type="submit" value="Submit Recipe">
            </div>
        </form>
    </div>
</body>
</html>
