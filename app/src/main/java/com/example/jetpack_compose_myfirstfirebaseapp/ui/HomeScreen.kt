package com.example.jetpack_compose_myfirstfirebaseapp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpack_compose_myfirstfirebaseapp.model.User
import com.example.jetpack_compose_myfirstfirebaseapp.viewmodel.HomeViewModel


@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    // Gọi hàm lấy dữ liệu ngay khi màn hình này hiện ra
    LaunchedEffect(Unit) {
        viewModel.fetchUsers()
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Danh sách người dùng", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn để hiển thị danh sách dài
        LazyColumn {
            items(viewModel.users){user->
                UserItem(user)
            }
        }
    }
}

// Giao diện của từng dòng (Item)
@Composable
fun UserItem(user: User) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier.size(40.dp),
                shape = MaterialTheme.shapes.small,
                color = MaterialTheme.colorScheme.primary
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(text = user.fullName.take(1), color = Color.White)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = user.fullName, style = MaterialTheme.typography.titleMedium)
                Text(text = "ID: ${user.id.take(5)}...", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}