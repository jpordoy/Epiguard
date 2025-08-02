package com.epilabs.epiguard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.epilabs.epiguard.R

@Composable
fun Component11(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 343.dp)
            .requiredHeight(height = 476.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(14.dp))
                .background(color = Color.White)
                .shadow(elevation = 16.dp,
                    shape = RoundedCornerShape(14.dp)))
        Text(
            text = "Fulano",
            color = Color(0xff2c2c2c),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxSize())
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(14.dp))
                .background(color = Color(0xff8863e4))
                .shadow(elevation = 16.dp,
                    shape = RoundedCornerShape(14.dp)))
        Number38()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = CircleShape)
                .background(color = Color.White))
        Badge(
            containerColor = Color(0xffff4d4d))
        Icon(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "bell",
            tint = Color(0xff2c2c2c),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 309.dp,
                    end = 14.dp,
                    top = 14.dp,
                    bottom = 442.dp))
        Text(
            text = "Administrador",
            color = Color(0xff8c8c8c),
            style = TextStyle(
                fontSize = 12.sp),
            modifier = Modifier
                .fillMaxSize())
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 0.dp,
                    y = 64.dp)
                .requiredWidth(width = 267.dp)
                .requiredHeight(height = 48.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = Color.White)
                    .shadow(elevation = 16.dp,
                        shape = RoundedCornerShape(14.dp)))
            Text(
                text = "Buscar...",
                color = Color(0xffaeaeae),
                style = TextStyle(
                    fontSize = 16.sp),
                modifier = Modifier
                    .fillMaxSize())
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
                tint = Color(0xffaeaeae),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp,
                        end = 229.dp,
                        top = 80.dp,
                        bottom = (-52).dp
                    ))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(14.dp))
                .background(color = Color(0xff8a19d6))
                .shadow(elevation = 16.dp,
                    shape = RoundedCornerShape(14.dp)))
        Icon(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "filter",
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 309.dp,
                    end = 14.dp,
                    top = 82.dp,
                    bottom = 374.dp))
        Icon(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "filter",
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 309.dp,
                    end = 14.dp,
                    top = 90.dp,
                    bottom = 366.dp))
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = Color(0xffb66dff),
            modifier = Modifier
                .clip(shape = RoundedCornerShape(14.dp))
                .padding(start = 8.dp,
                    top = 136.dp,
                    bottom = 184.dp)
                .shadow(elevation = 16.dp,
                    shape = RoundedCornerShape(14.dp))
        ) {
            Box(
                modifier = Modifier
                    .requiredWidth(width = 335.dp)
                    .requiredHeight(height = 156.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 167.dp,
                            y = 11.dp)
                        .requiredSize(size = 120.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 170.dp,
                            y = 14.dp)
                        .requiredWidth(width = 165.dp)
                        .requiredHeight(height = 218.dp))
                Image(
                    painter = painterResource(id = R.drawable.sms),
                    contentDescription = "acai 1",
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 152.dp,
                            y = (-15).dp)
                        .requiredSize(size = 150.dp)
                        .rotate(degrees = 12.76f))
                Text(
                    text = "Gerenciar",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 16.sp),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 22.dp,
                            y = 22.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 185.dp,
                            y = 38.dp)
                        .requiredSize(size = 120.dp)
                        .clip(shape = CircleShape)
                        .background(color = Color(0xff8130ac).copy(alpha = 0.5f)))
                Text(
                    text = "Estoque",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Black),
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 22.dp,
                            y = 45.dp)
                        .requiredWidth(width = 132.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.TopStart)
                        .offset(x = 22.dp,
                            y = 92.dp)
                        .requiredWidth(width = 130.dp)
                        .requiredHeight(height = 42.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .requiredWidth(width = 130.dp)
                            .requiredHeight(height = 42.dp)
                            .clip(shape = RoundedCornerShape(14.dp))
                            .background(color = Color(0xff8a19d6)))
                    Text(
                        text = "Acessar",
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 16.sp),
                        modifier = Modifier
                            .align(alignment = Alignment.TopStart)
                            .offset(x = 39.dp,
                                y = 11.dp))
                }
            }
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 143.dp,
                    y = 307.dp)
                .requiredWidth(width = 64.dp)
                .requiredHeight(height = 8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = MaterialTheme.shapes.small)
                    .background(color = Color(0xff8a19d6)))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffe3e1e8)))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffe3e1e8)))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = CircleShape)
                    .background(color = Color(0xffe3e1e8)))
        }
        Box(
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(x = 8.dp,
                    y = 332.dp)
                .requiredWidth(width = 335.dp)
                .requiredHeight(height = 144.dp)
        ) {
            Text(
                text = "Estatísticas",
                color = Color(0xff03314b),
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize())
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = Color(0xff8a19d6)))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = Color(0xff8a19d6)))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = RoundedCornerShape(14.dp))
                    .background(color = Color(0xff8a19d6)))
            Icon(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "graph-01",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 163.dp,
                        end = 148.dp,
                        top = 394.dp,
                        bottom = (-274).dp
                    ))
            Icon(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "bar-chart-01",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 283.dp,
                        end = 28.dp,
                        top = 394.dp,
                        bottom = (-274).dp
                    ))
            Icon(
                painter = painterResource(id = R.drawable.sms),
                contentDescription = "bar-chart-02",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 46.dp,
                        end = 265.dp,
                        top = 395.dp,
                        bottom = (-275).dp
                    ))
            Text(
                text = "920",
                color = Color.White,
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize())
            Text(
                text = "52",
                color = Color.White,
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize())
            Text(
                text = "R$ 9mil",
                color = Color.White,
                lineHeight = 1.3.em,
                style = TextStyle(
                    fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxSize())
            Text(
                text = "Produtos",
                color = Color.White,
                lineHeight = 2.6.em,
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .fillMaxSize())
            Text(
                text = "Vendidos",
                color = Color.White,
                lineHeight = 2.6.em,
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .fillMaxSize())
            Text(
                text = "Lucro",
                color = Color.White,
                lineHeight = 2.6.em,
                style = TextStyle(
                    fontSize = 10.sp),
                modifier = Modifier
                    .fillMaxSize())
        }
    }
}

@Composable
fun Number38(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(14.dp))
            .padding(start = 8.dp,
                end = 287.dp,
                top = 4.dp,
                bottom = 424.dp)
    ) {
        Property1Default()
        Image(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "image 41",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize())
    }
}

@Composable
fun Property1Default(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xff8a19d6)))
}

@Preview(widthDp = 343, heightDp = 476)
@Composable
private fun Component11Preview() {
    Component11(Modifier)
}